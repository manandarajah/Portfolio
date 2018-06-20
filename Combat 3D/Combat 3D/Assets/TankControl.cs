using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TankControl : MonoBehaviour {

    public float moveSpeed = 1, turnSpeed = 1;
    private float delayTime = 0.2f, counter = 0.0f;
    public GameObject bullet;
    protected GameObject tank;
    protected Rigidbody rb;
    public bool wasHit = false;
    protected bool fire = false, move = false;

    // Use this for initialization
    void Start () {
        
	}

    public void TankCtrlUpdate()
    {
        /*if (Input.GetButton("left") 
            || Input.GetButton("right")
            || Input.GetButton("up")
            || Input.GetButton("down"))
            tank = GameObject.Find("PlayerTank");
        else if (Input.GetButton("a") 
            || Input.GetButton("d")
            || Input.GetButton("s")
            || Input.GetButton("w"))
            tank = GameObject.Find("AITank");*/
        
        if (move)
        {
            move = false;
            float translation = Input.GetAxis("Vertical") * moveSpeed, rotation = Input.GetAxis("Horizontal") * turnSpeed;
            translation *= Time.deltaTime;
            rotation *= Time.deltaTime;
            tank.transform.Translate(0, 0, translation);
            tank.transform.RotateAround(Vector3.up, rotation);
            SoundMgr.MoveTank.Play();
            rb.velocity = Vector3.zero;
        }

        if (fire && counter > delayTime)
        {
            fire = false;
            GameObject turret = tank.transform.FindChild("Turret").gameObject;
            //SphereCollider sc = turret.GetComponentInChildren<SphereCollider>();
            //sc.enabled = false;
            GameObject arm = turret.transform.FindChild("Arm").gameObject;
            Transform tf = arm.GetComponent<Transform>();
            SoundMgr.TankFire.Play();
            //Transform tf = this.gameObject.GetComponent<Transform>();

            if (tf != null && bullet != null)
            {
                Instantiate(bullet, tf.position, tf.rotation);
                //sc.enabled = true;
                counter = 0;
            }
        }

        if (wasHit)
        {
            /*Destroy(this);
            Transform tf = this.gameObject.GetComponent<Transform>();*/
            this.gameObject.transform.RotateAround(Vector3.up, 1);

            if (counter > delayTime + 1)
            {
                counter = 0;
                wasHit = false;
            }
        }
        counter += Time.deltaTime;
    }
	
	// Update is called once per frame
	void Update () {
        //TankCtrlUpdate();
	}
}
