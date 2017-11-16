using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Final_ProjectV1
{
    public partial class BusTransitForm : Form
    {
        MyScheduleForm mySchedule;
        CreateNewForm createform;
        BusScheduleForm bus;

        public BusTransitForm()
        {
            InitializeComponent();
        }

        private void BusTransitForm_Load(object sender, EventArgs e)
        {
            this.bus = new BusScheduleForm();
            this.bus.SetForm(this);
            this.createform = new CreateNewForm();
            mySchedule = new MyScheduleForm();
            mySchedule.SetForm(this);
        }

        public void SetForm(CreateNewForm createform)
        {
            this.createform = createform;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void btnBusSchedule_Click(object sender, EventArgs e)
        {
            this.bus.Show();
            this.Hide();
        }

        private void btnMySchedule_Click(object sender, EventArgs e)
        {
            this.mySchedule.Show();
            this.Hide();
        }
    }
}
