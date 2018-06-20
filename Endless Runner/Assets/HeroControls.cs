using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HeroControls : MonoBehaviour {

    protected Camera camera;
    protected Rigidbody rb;
    protected Animator anim;
    public int moveSpeed;
    protected bool jump = false, slide = false;

	// Use this for initialization
	void Start () {
        
	}
	
    protected void HeroControllerUpdate()
    {
        if (GamePlay.Run)
        {
            this.transform.position += Vector3.forward * Time.deltaTime * moveSpeed;
            float offset = this.transform.position.z - camera.transform.position.z;
            //camera.transform.position += Vector3.forward * Time.deltaTime * moveSpeed;
            camera.transform.Translate(offset,0,0);
            rb.velocity = Vector3.zero;

            if (Input.GetKey(KeyCode.UpArrow))
                jump = true;
            else if (Input.GetKey(KeyCode.DownArrow))
                slide = true;
        }

        else
            anim.Stop();
    }

	// Update is called once per frame
	void Update () {
        //HeroControllerUpdate();
	}
}
