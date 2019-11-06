using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Intro_to_ASP.NET_MVC.Controllers
{
    public class JunkController : Controller
    {
        // GET: Junk
        public ActionResult Index()
        {
            return View();
        }
    }
}