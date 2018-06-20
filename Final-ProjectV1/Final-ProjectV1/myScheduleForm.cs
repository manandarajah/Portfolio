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
    public partial class MyScheduleForm : Form
    {
        BusTransitForm newform;
        CreateNewForm createform = new CreateNewForm();
        List<Schedule> list;
        List<string> listString;
        bool edit = false;

        public MyScheduleForm()
        {
            InitializeComponent();
        }

        private void MyScheduleForm_Load(object sender, EventArgs e)
        {
            this.list = new List<Schedule>();
            this.listString = new List<string>();
            this.createform.SetForm(this);

            using (StreamReader sr = new StreamReader("../../favouriteSchedule.txt"))
            {
                string line;

                while ((line = sr.ReadLine()) != null)
                {
                    this.listString.Add(line);
                    string[] data = new string[4];
                    int count = 0;

                    for (int i = 0; i < data.Length; i++)
                    {
                        data[i] = "";
                    }
                    string code = "\u0092";

                    for (int i = 0; i < line.Length; i++)
                    {
                        if (!line[i].Equals('-'))
                        {
                            data[count] += line[i];
                        }

                        else if (!data[count].Equals("") && line[i].Equals('-') && line[i+1].Equals('-'))
                        {
                            count++; 
                        }
                    }
                    Schedule s = new Schedule(data[0],data[1],data[2],data[3]);
                    this.list.Add(s);
                    this.listBox1.Items.Add(s.GetSchedule());
                }
            }
        }

        public void SetForm(BusTransitForm newform)
        {
            this.newform = newform;
        }

        public void LoadToBox(Schedule item)
        {
            if (edit)
            {
                for (int i = 0; i < this.list.Count; i++)
                {
                    if (this.listBox1.SelectedItem.Equals(this.list.ElementAt(i).GetSchedule()))
                    {
                        this.list.RemoveAt(i);
                        this.list.Insert(i, item);
                        this.listBox1.Items.RemoveAt(i);
                        this.listBox1.Items.Insert(i, item.GetSchedule());
                        this.listString.RemoveAt(i);
                        this.listString.Insert(i, item.GetScheduleText());
                        File.WriteAllLines("../../favouriteSchedule.txt", this.listString);
                        break;
                    }
                }
                edit = false;
            }

            else
            {
                this.list.Add(item);
                this.listString.Add(item.GetScheduleText());
                this.listBox1.Items.Add(item.GetSchedule());
                File.WriteAllLines("../../favouriteSchedule.txt", listString);
            }
        }

        private void EditFavourite(object sender, EventArgs e)
        {
            for (int i = 0; i < list.Count; i++)
            {
                if (this.listBox1.SelectedItem.Equals(this.list.ElementAt(i).GetSchedule()))
                {
                    this.createform.Edit(this.list.ElementAt(i));
                }
            }
            edit = true;
        }

        private void MainMenu(object sender, EventArgs e)
        {
            this.Hide();
            this.newform.Show();
        }

        private void CreateFavourite(object sender, EventArgs e)
        {
            this.createform.Show();
        }

        private void RemoveFavourite_Click(object sender, EventArgs e)
        {

            for (int i = 0; i < list.Count; i++)
            {
                if (this.listBox1.SelectedItem.Equals(this.list.ElementAt(i).GetSchedule()))
                {
                    this.listBox1.Items.Remove(this.list.ElementAt(i).GetSchedule());
                    listString.Remove(this.list.ElementAt(i).GetScheduleText());
                    list.Remove(this.list.ElementAt(i));
                }
            }
            File.WriteAllLines("../../favouriteSchedule.txt", listString);
        }
    }
}
