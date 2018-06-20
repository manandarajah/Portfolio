using UnityEngine;
using System.Collections;

public class PlayerControls : MonoBehaviour {

	// in here, we will handle button presses... and then pass them into the animator
	// controller component which will then set paramteers...and then make anim 
	// state changes FTW!
	public Animator anim;

	// Use this for initialization
	void Start () {
	
	}

	public void OnEnable()
	{
		anim = this.gameObject.GetComponent<Animator>();
	}

	public void PlayerControlsUpdate()
	{
		if (anim != null)
		{
			// if <space> is presed, set the param for the kick move!
			if (Input.GetKeyDown(KeyCode.Space) == true)
			{
				//anim.CrossFade("CrescentKick", 0.25f);
				anim.SetTrigger("kick");
			}

			if (Input.GetKeyDown(KeyCode.J) == true)
			{
				anim.SetTrigger("jump");
			}

			if (Input.GetKey(KeyCode.W) == true)
				anim.SetBool("walking", true);
			else
				anim.SetBool("walking", false);

		}
	}

	// Update is called once per frame
	void Update () {
	
		PlayerControlsUpdate();
	}
}
