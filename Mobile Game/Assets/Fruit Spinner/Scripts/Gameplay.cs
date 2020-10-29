using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Gameplay : MonoBehaviour
{
    public static int score = 0;
    public int barDepleterSpeed, barRestoreSpeed;
    private int buttonPoint = 2000, connectors = 1, juiceBoxChance = 5, indexer = 0;
    public float energy;
    private float maxEnergy, buttonTimer = 0;
    private bool allSpinning = true, initialSpin = false, canGoToNextLevel = false;
    public GameObject connector;
    GameObject juiceBoxSpawn, connectorSpawn, instructions;
    Vector3 originalJuiceBoxSpawn;
    public GameObject juiceBox;
    GameObject[] levels;
    Button brewJuice, useConnector, nextLevel, quitGame, playAgain, start, play;
    Text scoreText, connectorButtonText;
    Scrollbar energyBar;

    // Start is called before the first frame update
    void Start()
    {
        juiceBoxSpawn = GameObject.Find("JuiceBoxSpawn");
        originalJuiceBoxSpawn = juiceBoxSpawn.transform.position;
        brewJuice = GameObject.Find("BrewJuice").GetComponent<Button>();
        brewJuice.onClick.AddListener(GenerateJuiceBox);
        brewJuice.gameObject.SetActive(false);
        nextLevel = GameObject.Find("NextLevel").GetComponent<Button>();
        nextLevel.onClick.AddListener(NextLevel);
        nextLevel.gameObject.SetActive(false);
        quitGame = GameObject.Find("QuitGame").GetComponent<Button>();
        quitGame.onClick.AddListener(QuitGame);
        quitGame.gameObject.SetActive(false);
        playAgain = GameObject.Find("PlayAgain").GetComponent<Button>();
        playAgain.onClick.AddListener(PlayAgain);
        playAgain.gameObject.SetActive(false);
        start = GameObject.Find("Start").GetComponent<Button>();
        start.onClick.AddListener(DisplayInstructions);
        play = GameObject.Find("Play").GetComponent<Button>();
        play.onClick.AddListener(StartGame);
        play.gameObject.SetActive(false);
        instructions = GameObject.Find("Instructions");
        instructions.SetActive(false);
        scoreText = GameObject.Find("Score").GetComponent<Text>();
        scoreText.text += score;
        energyBar = GameObject.Find("EnergyBar").GetComponent<Scrollbar>();
        maxEnergy = energy;

        levels = new GameObject[10];

        for (int i = 0; i < levels.Length; ++i)
        {
            levels[i] = GameObject.Find("Level" + (i + 1));
            levels[i].SetActive(false);
        }
        
        if (GameObject.Find("Connector").GetComponent<Connector>() != null)
        {
            connectorSpawn = GameObject.Find("ConnectorSpawner");
            useConnector = GameObject.Find("ConnectorButton").GetComponent<Button>();
            useConnector.onClick.AddListener(GenerateConnector);
            connectorButtonText = GameObject.Find("ConnectorButtonText").GetComponent<Text>();
            connectorButtonText.text = connectors + "";
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.touchCount > 0)
        {
            Touch touch = Input.GetTouch(0);
            Ray raycast = Camera.main.ScreenPointToRay(new Vector3(touch.position.x, touch.position.y, 10));
            RaycastHit hitInfo;

            if (Physics.Raycast(raycast, out hitInfo))
            {
                if (touch.phase == TouchPhase.Moved)
                {
                    Vector3 touchPos = Camera.main.ScreenToWorldPoint(new Vector3(touch.position.x, touch.position.y, 10));

                    if (hitInfo.collider.gameObject.GetComponent<Fruit>() != null && energy > 0)
                    {
                        Fruit fruit = hitInfo.collider.gameObject.GetComponent<Fruit>();

                        if (!fruit.onConnector)
                        {
                            allSpinning = true;
                            fruit.Spin(touchPos);

                            if (fruit.GetScore() >= 1)
                            {
                                energy -= 0.1f * (Time.deltaTime * barDepleterSpeed);
                                energy -= 0.1f * (Time.deltaTime * barDepleterSpeed);
                            }
                        }
                    }

                    else if (hitInfo.collider.gameObject.GetComponent<Connector>() != null)
                    {
                        Connector connector = hitInfo.collider.gameObject.GetComponent<Connector>();

                        if (!connector.isConnected)
                        {
                            connector.Move(touchPos);
                        }

                        else
                        {
                            connector.Spin(touchPos);
                        }
                    }
                }
                Debug.Log("Touched");
            }
        }
        
        if (energy > 0 && energy < maxEnergy)
        {
            energy += 0.1f * Time.deltaTime * barRestoreSpeed;
        }

        Fruit[] fruits = levels[indexer].GetComponentsInChildren<Fruit>();

        foreach (Fruit fruit in fruits)
        {
            if (fruit.GetSpeed() <= 0)
            {
                allSpinning = false;
            }
        }

        initialSpin = true;

        foreach (Fruit fruit in fruits)
        {
            if (fruit.GetSpeed() == 0)
            {
                initialSpin = false;
            }
        }

        if (score > 0 && score >= buttonPoint && allSpinning && juiceBoxChance > 0)
        {
            SetBrewJuice(true);
            buttonPoint += 2000;
            buttonTimer = 4;
        } 
        energyBar.size = (energy / maxEnergy);
        scoreText.text = "Score: " + score;

        if (buttonTimer > 0)
        {
            buttonTimer -= Time.deltaTime;
        }

        else if (buttonTimer < 0)
        {
            SetBrewJuice(false);
            ResetButtonTimer();
            DecrementJuiceBoxChance();
        }

        if (juiceBoxChance == 0 || (!allSpinning && initialSpin))
        {
            EndLevel();
        }
    }

    void DisplayInstructions()
    {
        start.gameObject.SetActive(false);
        instructions.SetActive(true);
        play.gameObject.SetActive(true);
    }

    void StartGame()
    {
        play.gameObject.SetActive(false);
        instructions.SetActive(false);
        levels[indexer].SetActive(true);
    }

    void GenerateJuiceBox()
    {
        GameObject tempJB = Instantiate(juiceBox, juiceBoxSpawn.transform.position, Quaternion.identity);
        juiceBoxSpawn.transform.Translate(Vector3.right);
        DecrementJuiceBoxChance();
        ResetButtonTimer();
        SetBrewJuice(false);
    }

    void GenerateConnector()
    {
        if (connectors > 0 && connector != null)
        {
            Instantiate(connector, connectorSpawn.transform.position, Quaternion.identity);
            --connectors;
            connectorButtonText.text = "" + connectors;
        }
    }

    void EndLevel()
    {
        levels[indexer].SetActive(false);
        quitGame.gameObject.SetActive(true);

        if (juiceBoxChance > 0)
        {
            playAgain.gameObject.SetActive(true);
        }

        else if (!levels[indexer].name.Equals("Level10"))
        {
            nextLevel.gameObject.SetActive(true);
        }
    }

    void NextLevel()
    {
        ++indexer;
        canGoToNextLevel = true;
        PlayAgain();
    }

    void QuitGame()
    {
        Application.Quit();
    }

    void PlayAgain()
    {
        scoreText.text = "Play again";
        GameObject[] juiceboxes = GameObject.FindGameObjectsWithTag("Juice");

        foreach (GameObject juicebox in juiceboxes)
        {
            Destroy(juicebox);
        }


        nextLevel.gameObject.SetActive(false);
        playAgain.gameObject.SetActive(false);
        quitGame.gameObject.SetActive(false);
        initialSpin = false;
        levels[indexer].SetActive(true);

        if (!canGoToNextLevel)
        {
            Fruit[] fruits = levels[indexer].GetComponentsInChildren<Fruit>();

            foreach (Fruit fruit in fruits)
            {
                fruit.ResetSpinSpeed();
            }
        }

        else
        {
            canGoToNextLevel = false;
        }

        energy = maxEnergy;
        juiceBoxSpawn.transform.position = originalJuiceBoxSpawn;
        juiceBoxChance = 5;
    }

    private void SetBrewJuice(bool value)
    {
        brewJuice.gameObject.SetActive(value);
    }

    private void ResetButtonTimer()
    {
        buttonTimer = 0;
    }

    private void DecrementJuiceBoxChance()
    {
        --juiceBoxChance;
    }
}