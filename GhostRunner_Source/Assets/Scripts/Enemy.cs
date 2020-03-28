using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class Enemy : MonoBehaviour
{
    private NavMeshAgent agent;
    private GameObject target;

    // Start is called before the first frame update
    void Start()
    {
        agent = this.GetComponent<NavMeshAgent>();
        target = GameObject.Find("exo_red");
    }

    // Update is called once per frame
    void Update()
    {
        if (Vector3.Distance(target.transform.position, agent.gameObject.transform.position) < 10)
        {
            agent.SetDestination(target.transform.position);
        }
    }
}
