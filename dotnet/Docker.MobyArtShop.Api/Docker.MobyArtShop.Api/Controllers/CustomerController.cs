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
using Docker.MobyArtShop.Api.Models;
using Docker.MobyArtShop.Api.Models.Shop;
using Microsoft.Owin.Security;
using Microsoft.AspNet.Identity.Owin;
using Microsoft.AspNet.Identity;

namespace Docker.MobyArtShop.Api.Controllers
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

        // POST: api/Customer
        [ResponseType(typeof(Customer))]
        public async Task<IHttpActionResult> PostCustomer(Customer model)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var user = new ApplicationUser()
            {
                Address = model.Address,
                Name = model.Name,
                Role = model.Role,
                PhoneNumber = model.Phone,
                UserName = model.Username,
                Email = model.Email,
                CustomerId = _Random.Next() //HACK - the membership API uses a string ID
            };

            try
            {
                IdentityResult result = await UserManager.CreateAsync(user, model.Password);
                if (!result.Succeeded)
                {
                    return BadRequest();
                }
            }
            catch(Exception ex)
            {

            }            
            
            return CreatedAtRoute("DefaultApi", new { id = user.Id }, model);
        }

        [ResponseType(typeof(Customer))]
        [Route("username/{userName}")]
        public async Task<IHttpActionResult> GetCustomerByUsername(string userName)
        {
            var user = await UserManager.FindByNameAsync(userName);
            if (user == null)
            {
                return NotFound();
            }

            var customer = new Customer
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
    }
}