namespace Final_ProjectV1
{
    partial class BusTransitForm
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
            this.btnBusSchedule = new System.Windows.Forms.Button();
            this.btnMySchedule = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btnBusSchedule
            // 
            this.btnBusSchedule.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.btnBusSchedule.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnBusSchedule.Location = new System.Drawing.Point(87, 87);
            this.btnBusSchedule.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.btnBusSchedule.Name = "btnBusSchedule";
            this.btnBusSchedule.Size = new System.Drawing.Size(147, 45);
            this.btnBusSchedule.TabIndex = 0;
            this.btnBusSchedule.Text = "Bus Schedule";
            this.btnBusSchedule.UseVisualStyleBackColor = false;
            this.btnBusSchedule.Click += new System.EventHandler(this.btnBusSchedule_Click);
            // 
            // btnMySchedule
            // 
            this.btnMySchedule.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.btnMySchedule.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnMySchedule.Location = new System.Drawing.Point(87, 139);
            this.btnMySchedule.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.btnMySchedule.Name = "btnMySchedule";
            this.btnMySchedule.Size = new System.Drawing.Size(147, 44);
            this.btnMySchedule.TabIndex = 1;
            this.btnMySchedule.Text = "My Favorite Schedule";
            this.btnMySchedule.UseVisualStyleBackColor = false;
            this.btnMySchedule.Click += new System.EventHandler(this.btnMySchedule_Click);
            // 
            // btnExit
            // 
            this.btnExit.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.btnExit.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnExit.Location = new System.Drawing.Point(87, 191);
            this.btnExit.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(147, 44);
            this.btnExit.TabIndex = 2;
            this.btnExit.Text = "Exit";
            this.btnExit.UseVisualStyleBackColor = false;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.Maroon;
            this.label1.Location = new System.Drawing.Point(32, 45);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(258, 24);
            this.label1.TabIndex = 3;
            this.label1.Text = "Welcome to Brampton Transit";
            // 
            // BusTransitForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.AppWorkspace;
            this.ClientSize = new System.Drawing.Size(312, 279);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.btnMySchedule);
            this.Controls.Add(this.btnBusSchedule);
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.Name = "BusTransitForm";
            this.Text = "Bus Transit App";
            this.Load += new System.EventHandler(this.BusTransitForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnBusSchedule;
        private System.Windows.Forms.Button btnMySchedule;
        private System.Windows.Forms.Button btnExit;
        private System.Windows.Forms.Label label1;
    }
}

