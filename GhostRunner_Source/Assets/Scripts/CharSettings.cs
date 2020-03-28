using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class CharSettings : MonoBehaviour
{
    Rigidbody rb;
    Scrollbar health;
    Text coinsCollected, orbsCollected, counter;
    GameObject forceField;
    ParticleSystem forceFieldSystem;
    Animator anim;
    Vector3 moveDirection;
    int coinCount = 0, orbCount = 0;
    float timer = -1, blend = 0.0f, speed = 5, prevSpeed, rotateSpeed = 5, gravity = 100;
    bool forcefieldActive = false, onMud = false, onHealth = false;

    // Start is called before the first frame update
    void Start()
    {
        rb = this.GetComponent<Rigidbody>();
        forceField = this.GetComponentInChildren<MeshRenderer>().gameObject;
        forceFieldSystem = GameObject.Find("OrbsSystem").GetComponent<ParticleSystem>();
        health = GameObject.Find("Healthbar").GetComponent<Scrollbar>();
        coinsCollected = GameObject.Find("CoinsCollected").GetComponent<Text>();
        orbsCollected = GameObject.Find("OrbsCollected").GetComponent<Text>();
        counter = GameObject.Find("Counter").GetComponent<Text>();
        anim = this.GetComponent<Animator>();
        //rb.velocity = Vector3.zero;
        Vector3 extraGravityForce = (Physics.gravity * gravity) - Physics.gravity;
        rb.AddForce(extraGravityForce);
    }

    // Update is called once per frame
    void Update()
    {
        rb.velocity = Vector3.zero;

        if (rb.velocity == Vector3.zero)
        {
            moveDirection = new Vector3(Input.GetAxis("Horizontal"), 0, Input.GetAxis("Vertical"));
            moveDirection = transform.TransformDirection(moveDirection);

            //moveDirection *= speed;

            if (moveDirection.z > 0 && !forcefieldActive && !onMud)
            {
                blend = 0.5f;
                speed = 5;
            }

            else if (moveDirection.z == 0)
            {
                blend = 0;
            }
        }
        //transform.Translate(moveDirection);
        rb.MovePosition(transform.position + moveDirection * Time.deltaTime * speed);

        //float curSpeed = speed * Input.GetAxis("Vertical");
        transform.Rotate(0,
         Input.GetAxis("Horizontal") * rotateSpeed, 0);
        /*Vector3 forward =
         transform.TransformDirection(Vector3.forward);*/

        if (Input.GetKey(KeyCode.F) && coinCount > 0 && !forcefieldActive)
        {
            forcefieldActive = true;
            timer = coinCount * 5;
            
            if (coinCount > 12)
            {
                coinCount -= 12;
                timer = 60;
            }

            else
            {
                coinCount = 0;
            }
            blend = 1;
            speed = 10;
        }

        if (Input.GetKey(KeyCode.G) && orbCount > 0 && !forcefieldActive)
        {
            forcefieldActive = true;
            timer = orbCount * 30;
            
            if (orbCount > 2)
            {
                orbCount -= 2;
                timer = 60;
            }

            else
            {
                orbCount = 0;
            }
            blend = 1;
            speed = 10;
        }

        if (forcefieldActive)
        {
            counter.text = "" + (int) timer;
            timer -= Time.deltaTime;
        }

        if (onHealth)
        {
            counter.text = "" + (int) timer;
            timer -= Time.deltaTime;

            if (health.size < 1)
            {
                health.size += .1f;
            }
        }

        if (timer < 1 && timer > 0)
        {
            forcefieldActive = false;
            onHealth = false;
            counter.text = "";
            timer = -1;
        }

        if (forcefieldActive)
        {
            forceFieldSystem.Play();
        }

        else
        {
            forceFieldSystem.Stop();
        }
        forceField.SetActive(forcefieldActive);
        coinsCollected.text = "Forcefield Coins: " + coinCount;
        orbsCollected.text = "Forcefield Orbs: " + orbCount;
        anim.SetFloat("Blend", blend, 1f, Time.deltaTime * 10f);
        counter.color = Color.white;
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag.Equals("Coin"))
        {
            ++coinCount;
            Destroy(collision.gameObject);
        }

        else if (collision.gameObject.tag.Equals("Orb"))
        {
            ++orbCount;
            Destroy(collision.gameObject);
        }

        else if (collision.gameObject.tag.Equals("Health") && !forcefieldActive)
        {
            onHealth = true;
            timer = 5;
            Destroy(collision.gameObject);
        }

        if (collision.gameObject.tag.Equals("Obstacle") && !forcefieldActive)
        {
            if (collision.gameObject.name.Contains("Mud") && !onMud)
            {
                onMud = true;
                prevSpeed = speed;
                speed -= 2;
            }

            else
            {
                speed = prevSpeed;
                onMud = false;
            }

            if (collision.gameObject.name.Contains("Ghoul"))
            {
                health.size -= .2f;
            }

            else if (collision.gameObject.name.Contains("GrimReaper"))
            {
                health.size = 0;
            }

            else
            {
                health.size -= 0.1f;
            }

        }

        if (health.size == 0 || collision.gameObject.name.Equals("EndPoint"))
        {
            SceneManager.LoadScene(2);
        }

        else if (collision.gameObject.tag.Equals("Obstacle") && !collision.gameObject.name.Contains("Mud") && forcefieldActive)
        {
            Destroy(collision.gameObject);
        }
    }
}
