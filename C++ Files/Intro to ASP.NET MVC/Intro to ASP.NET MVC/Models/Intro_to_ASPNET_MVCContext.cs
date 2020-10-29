using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Intro_to_ASP.NET_MVC.Models
{
    public class Intro_to_ASPNET_MVCContext : DbContext
    {
        // You can add custom code to this file. Changes will not be overwritten.
        // 
        // If you want Entity Framework to drop and regenerate your database
        // automatically whenever you change your model schema, please use data migrations.
        // For more information refer to the documentation:
        // http://msdn.microsoft.com/en-us/data/jj591621.aspx
    
        public Intro_to_ASPNET_MVCContext() : base("name=Intro_to_ASPNET_MVCContext")
        {
        }

        public System.Data.Entity.DbSet<Intro_to_ASP.NET_MVC.Models.Team> Teams { get; set; }

        public System.Data.Entity.DbSet<Intro_to_ASP.NET_MVC.Models.Player> Players { get; set; }
    }
}
