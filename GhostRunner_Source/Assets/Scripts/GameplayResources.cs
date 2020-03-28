using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class GameplayResources : MonoBehaviour
{
    GameObject ghost, ghoul, grimreaper, spikes, coin, orb, health, orbSystem;

    // Start is called before the first frame update
    void Start()
    {
        ghost = GameObject.Find("Ghost");
        ghoul = GameObject.Find("Ghoul");
        grimreaper = GameObject.Find("GrimReaper");
        spikes = GameObject.Find("Spikes");
        coin = GameObject.Find("Coin");
        orb = GameObject.Find("ForcefieldOrb");
        health = GameObject.Find("Health");
        orbSystem = GameObject.Find("OrbsSystem");

        for (int i = 0; i < 50; ++i)
        {
            spikes.transform.position = new Vector3(spikes.transform.position.x + 10, 5.11f, 82.49f);
            Instantiate(spikes);
        }

        for (int i = 0; i < 100; ++i)
        {
            ghost.transform.position = new Vector3(Random.Range(0, 490), 0.887f, Random.Range(0, 497));
            Instantiate(ghost);
        }

        for (int i = 0; i < 100; ++i)
        {
            ghoul.transform.position = new Vector3(Random.Range(0, 490), 0.887f, Random.Range(0, 497));
            Instantiate(ghoul);
        }

        for (int i = 0; i < 50; ++i)
        {
            grimreaper.transform.position = new Vector3(Random.Range(0, 490), 0.887f, Random.Range(0, 497));
            Instantiate(grimreaper);
        }

        for (int i = 0; i < 100; ++i)
        {
            coin.transform.position = new Vector3(Random.Range(0, 490), 1, Random.Range(0, 497));
            Instantiate(coin);
        }

        for (int i = 0; i < 10; ++i)
        {
            orb.transform.position = new Vector3(Random.Range(0, 490), 1, Random.Range(0, 497));
            Instantiate(orb);
            orbSystem.transform.position = orb.transform.position;
            Instantiate(orbSystem);
        }

        for (int i = 0; i < 200; ++i)
        {
            health.transform.position = new Vector3(Random.Range(0, 490), 1, Random.Range(0, 497));
            Instantiate(health);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
