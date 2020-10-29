using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Intro_to_ASP.NET_MVC.Models
{
    public class Team
    {
        public int TeamId { get; set; }
        [Required]

        public string Name { get; set; }
        public string city { get; set; }
        public DateTime Founded { get; set; }

        public virtual ICollection<Player> Players { get; set; }
    }

    public class Player
    {
        public int PlayerId { get; set; }
        public string Name { get; set; }
        public int TeamId { get; set; }

        public virtual Team team { get; set; }
    }
}