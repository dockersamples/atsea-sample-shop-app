using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using Docker.AtSea.Api.Models;
using Docker.AtSea.Api.Models.Shop;
using Microsoft.Owin.Security;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.AspNet.Identity;

namespace Docker.AtSea.Api.Controllers
{
    public class CustomerController : ApiController
    {
        private static Random _Random = new Random();

        private ApplicationDbContext db = new ApplicationDbContext();
        private const string LocalLoginProvider = "Local";
        private ApplicationUserManager _userManager;

        public CustomerController()
        {
        }

        public CustomerController(ApplicationUserManager userManager, ISecureDataFormat<AuthenticationTicket> accessTokenFormat)
        {
            UserManager = userManager;
            AccessTokenFormat = accessTokenFormat;
        }

        public ApplicationUserManager UserManager
        {
            get
            {
                return _userManager ?? Request.GetOwinContext().GetUserManager<ApplicationUserManager>();
            }
            private set
            {
                _userManager = value;
            }
        }

        public ISecureDataFormat<AuthenticationTicket> AccessTokenFormat { get; private set; }

        // GET: api/customer
        public async Task<IHttpActionResult> GetCustomers()
        {
            var users = await db.Users.ToListAsync();
            var customers = users.Select(x => MapUserToCustomer(x));
            return Ok(customers.ToArray());
        }

        // GET: api/customer/5
        public async Task<IHttpActionResult> GetCustomer(long id)
        {
            var user = await db.Users.Where(x => x.CustomerId == id).FirstOrDefaultAsync();
            if (user == null)
            {
                return NotFound();
            }

            return Ok(MapUserToCustomer(user));
        }

        // POST: api/Customer
        public async Task<IHttpActionResult> PostCustomer(Customer model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            model.CustomerId = _Random.Next(); //HACK - the membership API uses a string ID
            var user = new ApplicationUser()
            {
                Address = model.Address,
                Name = model.Name,
                Role = model.Role,
                PhoneNumber = model.Phone,
                UserName = model.Username,
                Email = model.Email,
                CustomerId = model.CustomerId
            };

            try
            {
                IdentityResult result = await UserManager.CreateAsync(user, model.Password);
                if (!result.Succeeded)
                {
                    return BadRequest(string.Join(" ", result.Errors));
                }
            }
            catch(Exception ex)
            {

            }
            
            return Created("", new { customerId = user.CustomerId });
        }
        
        [Route("customer/username={userName}")]
        [HttpGet]
        public async Task<IHttpActionResult> GetCustomerByUsername(string userName)
        {
            var user = await UserManager.FindByNameAsync(userName);
            if (user == null)
            {
                return NotFound();
            }

            var customer = MapUserToCustomer(user);
            return Ok(customer);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private static Customer MapUserToCustomer(ApplicationUser user)
        {
            return new Customer
            {
                Address = user.Address,
                CustomerId = user.CustomerId,
                Email = user.Email,
                Enabled = (user.LockoutEnabled == false),
                Name = user.Name,
                Phone = user.PhoneNumber,
                Role = user.Role,
                Username = user.UserName
            };
        }
    }
}