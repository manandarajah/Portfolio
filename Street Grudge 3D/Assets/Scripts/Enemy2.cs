using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy2 : Enemy1 {
    Vector3 currentDest;
    public Transform patrolPoint;

    public override void Attack()
    {
        base.Attack();
    }

    public override IEnumerator AttackCooldown(int seconds)
    {
        return base.AttackCooldown(seconds);
    }

    public override void Idle()
    {
        float currentDistance = Vector3.Distance(transform.position, currentDest);

        if (currentDistance > 1)
        {
            agent.SetDestination(currentDest);
        }

        else if (currentDistance < 2)
        {
            SetDestination();
        }
    }

    // Use this for initialization
    void Start () {
        InheritStart();
        agent.speed = 4.5f;
        originalPos = transform.position;
        currentDest = patrolPoint.position;
	}
	
	// Update is called once per frame
	void Update () {
        InheritUpdate();

        if (!playerAttaked)
        {
            CheckDistance();
        }
    }

    void SetDestination()
    {
        currentDest = currentDest.Equals(patrolPoint.position) ? originalPos : patrolPoint.position;
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.tag.Equals("Enemy"))
        {
            SetDestination();
        }
    }
}
