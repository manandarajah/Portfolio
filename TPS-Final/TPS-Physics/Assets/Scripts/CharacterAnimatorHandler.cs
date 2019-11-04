using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterAnimatorHandler : MonoBehaviour
{
    public Animator anim;
    public CharacterIKHandler ikHandler;
    public PlayerWeaponController weaponController;
    // Start is called before the first frame update
    void Start()
    {
        anim = GetComponent<Animator>();
        ikHandler = GetComponent<CharacterIKHandler>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void SetHorizontal(float value)
    {
        anim.SetFloat("Horizontal", value);
    }

    public void SetVertical(float value)
    {
        anim.SetFloat("Vertical", value);
    }

    public void PlayReload(bool toggle)
    {
        if(toggle)
        {
            anim.SetTrigger("Reload");
        }
        else
        {
            weaponController.weaponCtrl.FinishReload();
        }
        ikHandler.ikActive = !toggle;
    }
}
