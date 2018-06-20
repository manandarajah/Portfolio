using UnityEngine;
using System.Collections;

public class HeroController : MonoBehaviour {

	// we want this thing to set the kick parameter on the animator fsm
	// when a button is pressed okay?!

	private Animator anim;
	public float v;
	public float h;

	public void OnEnable() //with a capital O
	{
		anim = this.gameObject.GetComponent<Animator>();

	}

	public void HeroControllerUpdate()
	{
		// in here, we check for button press.. and then
		// set the trigger if it is!
		if (anim != null)
		{
			if (Input.GetKeyDown(KeyCode.Space) == true)
			{
				anim.CrossFade("kick",0.1f);
				//anim.SetTrigger("kick");
			}
			if (Input.GetKeyDown(KeyCode.J) == true)
				anim.SetTrigger("jump");
			if (Input.GetKeyDown(KeyCode.K) == true)
				anim.SetTrigger("lariat");	

			// walking
			if (Input.GetKey(KeyCode.UpArrow) == true)
				anim.SetBool("walk", true);
			else
				anim.SetBool("walk", false);

			v = Input.GetAxis("Vertical");
			h = Input.GetAxis("Horizontal");
			anim.SetFloat("walkSpeed", v);
		} 
	}
	
	// Update is called once per frame
	void Update () {
	
		HeroControllerUpdate();
	}
}
