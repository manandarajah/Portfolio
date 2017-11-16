using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace Final_ProjectV1
{
    public partial class BusScheduleForm : Form
    {
        BusTransitForm main;

        public BusScheduleForm()
        {
            InitializeComponent();
        }

        public void SetForm(BusTransitForm main)
        {
            this.main = main;
        }

        private void BusScheduleForm_Load(object sender, EventArgs e)
        {
            main = new BusTransitForm();
            string content = "";

            FileStream fileStream = null;
            try
            {
                fileStream = new FileStream("../../schedule.txt", FileMode.OpenOrCreate, FileAccess.Read);

                StreamReader streamReader = new StreamReader(fileStream);
                while (streamReader.Peek() != -1)
                {
                    content += streamReader.ReadLine();
                    content += System.Environment.NewLine;
                }
                txtSchedule.Text = content;

            }
            catch (FileNotFoundException)
            {
                MessageBox.Show("File not found.");
            }
            catch (IOException ex)
            {
                MessageBox.Show(ex.Message, "IOException");

            }
            finally
            {
                if (fileStream != null)
                {
                    fileStream.Close();
                }
            }
        }

        private void btnMainMenu_Click(object sender, EventArgs e)
        {
            this.Hide();
            this.main.Show();
        }
    }
}
