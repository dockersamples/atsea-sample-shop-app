using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Docker.AtSea.Api.Models.Shop
{
    public class Customer
    {
        public long CustomerId { get; set; }

        public string Name { get; set; }

        public string Address { get; set; }

        public string Email { get; set; }

        public string Phone { get; set; }

        public string Username { get; set; }

        public string Password { get; set; }

        public bool Enabled { get; set; }

        public string Role { get; set; }
    }
}