using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public abstract class EnemyStateMachine : MonoBehaviour {
    protected States state = States.Idle;
    public GameObject player;
    protected NavMeshAgent agent;
    public bool playerAttaked = false;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
	}

    protected void InheritStart()
    {
        agent = GetComponent<NavMeshAgent>();
    }

    protected void InheritUpdate()
    {
        switch (state)
        {
            case States.Attack:
                Attack();
                break;
            case States.AttackCooldown:
                StartCoroutine(AttackCooldown(5));
                break;
            default:
                Idle();
                break;
        }

        StopCoroutine(AttackCooldown(0));
    }
    
    public abstract void Idle();
    public abstract void Attack();
    public abstract IEnumerator AttackCooldown(int seconds);
}

public enum States { Idle, Attack, AttackCooldown }