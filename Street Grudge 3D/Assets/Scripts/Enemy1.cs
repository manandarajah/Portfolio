using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy1 : EnemyStateMachine {
    protected Vector3 originalPos;
    float cooldownTime = 0;

    public override void Attack()
    {
        agent.SetDestination(player.transform.position);

        if (playerAttaked)
        {
            state = States.AttackCooldown;
        }
    }

    public override IEnumerator AttackCooldown(int seconds)
    {
        agent.enabled = false;
        cooldownTime += Time.deltaTime;
        yield return new WaitUntil(() => cooldownTime >= seconds);
        agent.enabled = true;
        playerAttaked = false;
        cooldownTime = 0;
    }

    public override void Idle()
    {
        if (!agent.destination.Equals(originalPos))
        {
            agent.SetDestination(originalPos);
        }
    }

    // Use this for initialization
    void Start () {
        InheritStart();
        originalPos = transform.position;
	}
	
	// Update is called once per frame
	void Update () {
        InheritUpdate();

        if (!playerAttaked)
        {
            CheckDistance();
        }
	}

    protected void CheckDistance()
    {
        float distanceSqr = (transform.position - player.transform.position).sqrMagnitude;

        if (distanceSqr < 50)
        {
            state = States.Attack;
        }

        else
        {
            state = States.Idle;
        }
    }
}
