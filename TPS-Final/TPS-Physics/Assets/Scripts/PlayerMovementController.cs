using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovementController : MonoBehaviour
{
    // Animation
    public CharacterAnimatorHandler animHandler;

    // Movement Vars
    [SerializeField]
    private float forwardMovementForce = 10;
    [SerializeField]
    private float sidewaysMovementForce = 5;
    [SerializeField]
    private float maxForwardVelocity = 10;
    [SerializeField]
    private float maxSidewaysVelocity = 5;
    [SerializeField]
    private float deadZone = 0.1f;

    // Rotattion Vars
    [SerializeField]
    private float rotationSpeed = 1;

    float horizontal;
    float vertical;
    float rotation;

    Rigidbody rb;
    public Quaternion ogRotation;

    // Cover
    public CoverTrigger currentCover;
    public bool inCover;
    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody>();
        ogRotation = transform.rotation;
    }

    // Update is called once per frame
    void Update()
    {
        if(!inCover)
        {
            Move();
        }
        TakeCover();
        Rotate();
    }

    void Move()
    {
        horizontal = Input.GetAxis("Horizontal");
        vertical = Input.GetAxis("Vertical");

        Vector3 currentVelocity = rb.velocity;

        //// Use dot product to get forward component of velocity
        //if(Mathf.Abs(Vector3.Dot(currentVelocity, transform.forward)) < maxForwardVelocity && Mathf.Abs(vertical) >= deadZone)
        //{
        //    rb.AddForce(forwardMovementForce * transform.forward * vertical, ForceMode.VelocityChange);
        //}

        //// Use dot product to get sideways component of velocity
        //if (Mathf.Abs(Vector3.Dot(currentVelocity, transform.right)) < maxSidewaysVelocity && Mathf.Abs(horizontal) >= deadZone)
        //{
        //    rb.AddForce(sidewaysMovementForce * transform.right * horizontal, ForceMode.VelocityChange);
        //}

        rb.velocity = maxForwardVelocity * vertical * transform.forward + maxSidewaysVelocity * horizontal * transform.right;

        if(Mathf.Abs(horizontal) < deadZone)
        {
            Vector3 horizontalComponent = Vector3.Dot(currentVelocity, transform.right) * transform.right;
            rb.velocity -= horizontalComponent;
        }

        if (Mathf.Abs(vertical) < deadZone)
        {
            Vector3 verticalComponent = Vector3.Dot(currentVelocity, transform.forward) * transform.forward;
            rb.velocity -= verticalComponent;
        }

        animHandler.SetHorizontal(horizontal);
        animHandler.SetVertical(vertical);
        //transform.parent.position = transform.position - transform.localPosition;
    }

    void Rotate()
    {
        rotation = Input.GetAxis("Mouse X");
        transform.Rotate(Vector3.up, rotation * rotationSpeed * Time.deltaTime, Space.Self);
    }

    void TakeCover()
    {
        if(currentCover != null && Input.GetKeyDown(KeyCode.Space))
        {
            inCover = !inCover;
            animHandler.anim.SetBool("TakeCover", inCover);
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if(other.GetComponent<CoverTrigger>() && currentCover == null)
        {
            currentCover = other.GetComponent<CoverTrigger>();
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.GetComponent<CoverTrigger>() && currentCover == other.GetComponent<CoverTrigger>())
        {
            currentCover = null;
        }
    }
}
