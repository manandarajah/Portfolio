using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Final_ProjectV1
{
    public class Schedule
    {
        private string name, bus, stop, time;
        public string Name { set { name = value; } get { return name; } }
        public string Bus { set { bus = value; } get { return bus; } }
        public string Stop { set { stop = value; } get { return stop; } }
        public string Time { set { time = value; } get { return time; } }

        public Schedule(string name, string bus, string stop, string time)
        {
            this.name = name;
            this.bus = bus;
            this.stop = stop;
            this.time = time;
        }

        public string GetSchedule()
        {
            return this.name + "        " + this.bus + "        " + this.stop + "        " + this.time;
        }

        public string GetScheduleText()
        {
            return this.name + "--------" + this.bus + "--------" + this.stop + "--------" + this.time;
        }
    }
}
