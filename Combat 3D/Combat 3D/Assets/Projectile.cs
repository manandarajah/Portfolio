using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Projectile : MonoBehaviour {

    private GameObject playerScoreObj, cpuScoreObj;
    private TextMesh playerScore, compScore;
    private bool hit = false;

    //Runs event when bullet collides with a game object. If object is a tank, then player score increments and enemy tank respawns
    public void OnCollisionEnter(Collision other)
    {
        SoundMgr.TankDamage.Play();

        if (other.gameObject.tag == "Block")
            Destroy(this.gameObject);

        if (other.gameObject.tag == "AITank" || other.gameObject.tag == "PlayerTank")
        {
            Destroy(this.gameObject);

            switch (other.gameObject.tag)
            {
                case "PlayerTank":
                    AIScore.score++;
                    compScore.text = "PLAYER 2\n\t" + AIScore.score;
                    hit = true;
                    break;
                case "AITank":
                    PlayerScore.score++;
                    playerScore.text = "PLAYER 1\n\t" + PlayerScore.score;
                    hit = true;
                    break;
            }

            if (hit)
            {
                GameMgr.Respawn(other.gameObject);

                if (other.gameObject.tag == "AITank")
                    GameMgr.Respawn(GameObject.Find("PlayerTank"));
                else
                    GameMgr.Respawn(GameObject.Find("AITank"));
                hit = false;
            }

            if (PlayerScore.score == GameMgr.MatchPoint || AIScore.score == GameMgr.MatchPoint)
            {
                GameMgr.LoadEndGame(PlayerScore.score);
            }
        }
    }

	// Use this for initialization
	void Start () {
        playerScoreObj = GameObject.Find("PlayerScore");
        cpuScoreObj = GameObject.Find("CPUScore");

        if (playerScoreObj != null)
            playerScore = playerScoreObj.GetComponent<TextMesh>();

        if (cpuScoreObj != null)
            compScore = cpuScoreObj.GetComponent<TextMesh>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
