using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class ManageScenes : MonoBehaviour
{
    Button start, quit;
    Scrollbar health;

    // Start is called before the first frame update
    void Start()
    {
        start = GameObject.Find("Play").GetComponent<Button>();
        quit = GameObject.Find("Quit").GetComponent<Button>();

        start.onClick.AddListener(GoToPlay);
        quit.onClick.AddListener(Application.Quit);

    }

    void GoToPlay()
    {
        SceneManager.LoadScene(1);
        health = CollisionScript.health;
    }

    // Update is called once per frame
    void Update()
    {
        if (health && health.size == 0)
        {
            SceneManager.LoadScene(2);
        }
    }
}
