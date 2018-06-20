using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameMgr : MonoBehaviour {

    private Button button;
    public static int MatchPoint = 5;

    private void Awake()
    {
        DontDestroyOnLoad(button);
    }

    // Use this for initialization
    void Start () {
        button = this.gameObject.GetComponentInChildren<Button>();

        if (button != null)
            button.onClick.AddListener(LoadGameScreen);
	}

    //Loads Gameplay Screen
    public void LoadGameScreen()
    {
        if (button.GetComponentInChildren<Text>().text == "Start Game")
        {
            SceneManager.LoadScene("Combat");
        }
        else
        {
            Application.Quit();
        }
    }

    //Loads last scene when the game ends, and displays who wins
    public static void LoadEndGame(int playerScore)
    {
        SceneManager.LoadScene("End Game Screen");
        StatusMessage.SetStatus(playerScore, MatchPoint);
    }
    
    //Respawns game object when it gets hit
    public static void Respawn(GameObject go)
    {
        go.transform.Translate(
            -go.transform.position.x, 0, -go.transform.position.z
        );
        TankControl tc = go.GetComponentInChildren<TankControl>();
        float x = Random.Range(-34, 36), z = Random.Range(-10, 20);

        if ((x <= -34 || x >= 36) && (z <= -10 || z >= 20))
        {
            Respawn(go);
        }
        Debug.Log(x + "\t" + z);
        Vector3 position = new Vector3(x, 0, z);
        go.transform.Translate(position);
        tc.wasHit = true;
    }
	// Update is called once per frame
	void Update () {
		
	}
}
