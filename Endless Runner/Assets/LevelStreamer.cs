using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LevelStreamer : MonoBehaviour {
    
    private GameObject road, redtoken, bluetoken, box, platform;
    private Vector3 boxPos, platPos, rtpos, btpos;
    private Quaternion rtrot, btrot;
    private Text GOText;
    private List<GameObject> roads = new List<GameObject>(), props = new List<GameObject>();
    const float x = 3.215286f, y = 0.9250048f;
    private float z = 6.153f, counter = 0, delayTime = 1f;
    private int index = 0, itrCount = 0, propDelayCount = 0, propsIndex = 0;

    public void OnCollisionEnter(Collision other)
    {
        if (other.gameObject.tag == "Block")
        {
            GamePlay.Run = false;
            SoundMgr.sources[3].Play();

            if (GOText != null)
                GOText.text = "Game Over";
        }

    }

    // Use this for initialization
    void Start () {
        redtoken = GameObject.Find("RedToken");
        bluetoken = GameObject.Find("BlueToken");
        box = GameObject.Find("Box");
        rtpos = GameObject.Find("RedToken (1)").transform.position;
        btpos = GameObject.Find("BlueToken (1)").transform.position;
        rtrot = GameObject.Find("RedToken (1)").transform.rotation;
        btrot = GameObject.Find("BlueToken (1)").transform.rotation;
        platform = GameObject.Find("Platform");
        boxPos = box.transform.position;
        platPos = platform.transform.position;
        GOText = GameObject.Find("GameOverText").GetComponent<Text>();
        road = GameObject.Find("Road 1 (3)");
        roads.Add(GameObject.Find("Road 1 (2)"));
        roads.Add(GameObject.Find("Road (1)"));
        roads.Add(GameObject.Find("Road"));
        roads.Add(GameObject.Find("Road 1 (1)"));
    }

    void LevelUpdate()
    {
        if (GamePlay.Run)
        {
            if (counter > delayTime)
            {
                z += 2.5f;
                rtpos.z += 3.5f;
                btpos.z += 3.5f;
                boxPos.z += 3.5f;
                platPos.z += 3.5f;
                Vector3 position = new Vector3(x, y, z);
                GameObject tmp = Instantiate(road, position, Quaternion.identity);
                roads.Add(tmp);
                propDelayCount++;

                if (propDelayCount % 5 == 0)
                {
                    GameObject tmpBox = Instantiate(box, boxPos, Quaternion.identity);
                    GameObject tmpPlat = Instantiate(platform, platPos, Quaternion.identity);
                    GameObject tmpR = Instantiate(redtoken, rtpos, rtrot);
                    GameObject tmpB = Instantiate(bluetoken, btpos, btrot);
                    props.Add(tmpBox);
                    props.Add(tmpPlat);
                }

                if (propDelayCount % 50 == 0)
                {
                    HeroControls hc = this.GetComponent<HeroControls>();
                    hc.moveSpeed *= 2;
                }
                itrCount++;
                counter = 0;
            }

            if (itrCount > 1)
            {
                GameObject[] tmps = roads.ToArray();

                //if (tmps[index] != null)
                //{
                Destroy(tmps[index]);
                index++;
                itrCount = 0;

                if (propDelayCount % 10 == 0)
                {
                    GameObject[] propTmps = props.ToArray();

                    Destroy(propTmps[propsIndex]);
                    Destroy(propTmps[propsIndex + 1]);
                    propsIndex += 2;
                }
                //}
            }
            counter += Time.deltaTime;
        }
    }
	
	// Update is called once per frame
	void Update () {
        LevelUpdate();
	}
}
