using System;
using System.IO;
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
    public partial class CreateNewForm : Form
    {
        MyScheduleForm myform;
        BusTransitForm main = new BusTransitForm();
        bool edit = false;
        //Form form;

        public CreateNewForm()
        {
            InitializeComponent();
        }

        private void CreateNewForm_Load(object sender, EventArgs e)
        {
            this.main.SetForm(this);

            using (StreamReader buses = new StreamReader("../../buses.txt"))
            {
                using (StreamReader stops = new StreamReader("../../stops.txt"))
                {
                    using (StreamReader schedule = new StreamReader("../../selectedSchedule.txt"))
                    {
                        string line;

                        while ((line = buses.ReadLine()) != null)
                        {
                            this.comboBox1.Items.Add(line);
                        }

                        while ((line = stops.ReadLine()) != null)
                        {
                            this.comboBox2.Items.Add(line);
                        }

                        while ((line = schedule.ReadLine()) != null)
                        {
                            this.comboBox4.Items.Add(line);
                        }
                    }
                }
            }
        }

        public void SetForm(MyScheduleForm myform)
        {
            this.myform = myform;
        }

        public void Edit(Schedule schedule)
        {
            this.textBox1.Text = schedule.Name;
            this.comboBox1.Text = schedule.Bus;
            this.comboBox2.Text = schedule.Stop;
            this.comboBox4.Text = schedule.Time;
            this.Show();
        }

        private void Save(object sender, EventArgs e)
        {
            Schedule schedule = new Schedule(this.textBox1.Text, this.comboBox1.SelectedItem.ToString(), this.comboBox2.SelectedItem.ToString(), this.comboBox4.SelectedItem.ToString());
            this.myform.LoadToBox(schedule);
            this.textBox1.Text = "";
            this.comboBox1.Text = "";
            this.comboBox2.Text = "";
            this.comboBox4.Text = "";
            this.Hide();
        }

        private void MainMenu(object sender, EventArgs e)
        {
            this.main.Show();
            this.myform.Hide();
            this.Hide();
        }

        private void Close(object sender, EventArgs e)
        {
            this.Hide();
        }
    }
}
