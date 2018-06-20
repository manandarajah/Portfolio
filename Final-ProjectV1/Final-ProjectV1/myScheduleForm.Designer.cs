namespace Final_ProjectV1
{
    partial class MyScheduleForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.lblMyFavouriteSchedule = new System.Windows.Forms.Label();
            this.RemoveFavourite = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(195, 217);
            this.button1.Margin = new System.Windows.Forms.Padding(2);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(167, 33);
            this.button1.TabIndex = 0;
            this.button1.Text = "Edit Favourite";
            this.button1.UseVisualStyleBackColor = false;
            this.button1.Click += new System.EventHandler(this.EditFavourite);
            // 
            // button2
            // 
            this.button2.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.button2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button2.Location = new System.Drawing.Point(24, 217);
            this.button2.Margin = new System.Windows.Forms.Padding(2);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(167, 33);
            this.button2.TabIndex = 1;
            this.button2.Text = "Create Favourite";
            this.button2.UseVisualStyleBackColor = false;
            this.button2.Click += new System.EventHandler(this.CreateFavourite);
            // 
            // button3
            // 
            this.button3.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.button3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button3.Location = new System.Drawing.Point(195, 254);
            this.button3.Margin = new System.Windows.Forms.Padding(2);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(167, 33);
            this.button3.TabIndex = 2;
            this.button3.Text = "Go to Main Menu";
            this.button3.UseVisualStyleBackColor = false;
            this.button3.Click += new System.EventHandler(this.MainMenu);
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.Location = new System.Drawing.Point(24, 42);
            this.listBox1.Margin = new System.Windows.Forms.Padding(2);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(511, 160);
            this.listBox1.TabIndex = 4;
            // 
            // lblMyFavouriteSchedule
            // 
            this.lblMyFavouriteSchedule.AutoSize = true;
            this.lblMyFavouriteSchedule.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblMyFavouriteSchedule.ForeColor = System.Drawing.Color.Maroon;
            this.lblMyFavouriteSchedule.Location = new System.Drawing.Point(182, 16);
            this.lblMyFavouriteSchedule.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.lblMyFavouriteSchedule.Name = "lblMyFavouriteSchedule";
            this.lblMyFavouriteSchedule.Size = new System.Drawing.Size(178, 20);
            this.lblMyFavouriteSchedule.TabIndex = 5;
            this.lblMyFavouriteSchedule.Text = "My Favourite Schedules";
            // 
            // RemoveFavourite
            // 
            this.RemoveFavourite.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.RemoveFavourite.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveFavourite.Location = new System.Drawing.Point(366, 217);
            this.RemoveFavourite.Margin = new System.Windows.Forms.Padding(2);
            this.RemoveFavourite.Name = "RemoveFavourite";
            this.RemoveFavourite.Size = new System.Drawing.Size(167, 33);
            this.RemoveFavourite.TabIndex = 6;
            this.RemoveFavourite.Text = "Remove Favourite";
            this.RemoveFavourite.UseVisualStyleBackColor = false;
            this.RemoveFavourite.Click += new System.EventHandler(this.RemoveFavourite_Click);
            // 
            // MyScheduleForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.AppWorkspace;
            this.ClientSize = new System.Drawing.Size(558, 313);
            this.Controls.Add(this.RemoveFavourite);
            this.Controls.Add(this.lblMyFavouriteSchedule);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "MyScheduleForm";
            this.Text = "My Schedule";
            this.Load += new System.EventHandler(this.MyScheduleForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.Label lblMyFavouriteSchedule;
        private System.Windows.Forms.Button RemoveFavourite;
    }
}