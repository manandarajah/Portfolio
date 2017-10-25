using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Hero : HeroControls {
    
    private MeshCollider[] colliders;

    // Use this for initialization
    void Start () {
        rb = this.GetComponent<Rigidbody>();
        anim = this.GetComponent<Animator>();
        camera = GameObject.Find("Main Camera").GetComponent<Camera>();
        colliders = this.GetComponents<MeshCollider>();
    }

    void HeroUpdate()
    {
        if (jump)
        {
            colliders[1].enabled = false;
            colliders[2].enabled = false;
            SoundMgr.sources[1].Play();
            anim.Play("Jump");
        }
        else if (slide) {
            colliders[0].enabled = false;
            SoundMgr.sources[2].Play();
            anim.Play("Run_Slide");
        }

        jump = false;
        slide = false;

        AnimatorStateInfo state = anim.GetCurrentAnimatorStateInfo(0);

        if (state.normalizedTime > 0.9f && (state.IsName("Jump") || state.IsName("Run_Slide")))
        {
            for (int i = 0; i < colliders.Length; i++)
            {
                colliders[i].enabled = true;
            }
        }
    }
	
    // Update is called once per frame
    void Update () {
        HeroControllerUpdate();
        HeroUpdate();
    }
}
