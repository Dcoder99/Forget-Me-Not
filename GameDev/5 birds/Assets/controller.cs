using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class controller : MonoBehaviour {

	// Initialization

	// Direction and Orientation
	int dir = -1, ort = -1;
	// Scaling Value and Spacing btw them
	float scaleVal = 0.025f, spacing = 0.5f;
	// Offset value to move all 5 birds right or left
	float offsetX = -1, offsetY = -1;
	// Enumeration for direction
	int RIGHT = 1, LEFT = 3, DOWN = 0, UP = 2;
	// Variable to store score
	public static int sc = -1;
	// Positive and negative increments to score.
	int pos = 10, neg = 1;
	// playTime denotes the total time game wil run for
	float timeLeft = float.PositiveInfinity, playTime = 30.0f;

	// has Swiped keeps a track of the first Swipe	
	bool hasSwiped = false;
	// Text object to dislpay score
	public Text score, tl;
	// Transform arrows which stores the arrows
	public Transform[] arrows;

	// Function to assign a random orientaion
	int assignOrientation(int orientation)
	{
		switch (orientation)
		{
			case 1:
				//CROSS
				arrows[0].position = new Vector3(0*spacing + offsetX,0*spacing + offsetY,0);		
				arrows[1].position = new Vector3(0*spacing + offsetX,-1*spacing + offsetY,0);
				arrows[2].position = new Vector3(-1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[3].position = new Vector3(1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[4].position = new Vector3(0*spacing + offsetX,1*spacing + offsetY,0);
				break;
			case 2:
				//Vertical Line
				arrows[0].position = new Vector3(0*spacing + offsetX,0*spacing + offsetY,0);		
				arrows[1].position = new Vector3(0*spacing + offsetX,-1*spacing + offsetY,0);
				arrows[2].position = new Vector3(0*spacing + offsetX,2*spacing + offsetY,0);
				arrows[3].position = new Vector3(0*spacing + offsetX,-2*spacing + offsetY,0);
				arrows[4].position = new Vector3(0*spacing + offsetX,1*spacing + offsetY,0);
				break;
			case 3:
				//Horizontal Line
				arrows[0].position = new Vector3(0*spacing + offsetX,0*spacing + offsetY,0);		
				arrows[1].position = new Vector3(-2*spacing + offsetX,0*spacing + offsetY,0);
				arrows[2].position = new Vector3(-1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[3].position = new Vector3(1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[4].position = new Vector3(2*spacing + offsetX,0*spacing + offsetY,0);
				break;
			case 4:
				//Up Arrow
				arrows[0].position = new Vector3(0*spacing + offsetX,1*spacing + offsetY,0);		
				arrows[1].position = new Vector3(-2*spacing + offsetX,-1*spacing + offsetY,0);
				arrows[2].position = new Vector3(-1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[3].position = new Vector3(1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[4].position = new Vector3(2*spacing + offsetX,-1*spacing + offsetY,0);
				break;
			case 5:
				//Down Arrow
				arrows[0].position = new Vector3(0*spacing + offsetX,-1*spacing + offsetY,0);		
				arrows[1].position = new Vector3(-2*spacing + offsetX,1*spacing + offsetY,0);
				arrows[2].position = new Vector3(-1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[3].position = new Vector3(1*spacing + offsetX,0*spacing + offsetY,0);
				arrows[4].position = new Vector3(2*spacing + offsetX,1*spacing + offsetY,0);
				break;				
			default:
				break;
		}
		return orientation;
	}

	// Function to assign a Random direction and Offset
	void assignDirection()
	{
		ort = Random.Range(1, 6);
		dir = Random.Range(0, 4);
		offsetX = Random.Range(-1, 2);
		offsetY = Random.Range(-1, 2);
		assignOrientation(ort);

		arrows[0].eulerAngles = new Vector3(0, 0, 90*dir);
		for(int i=1; i<=4; i++)
		{
			arrows[i].Rotate(0, 0, 90*Random.Range(0,4));
		}
		Debug.Log(message: "DIRECTION: " + dir.ToString());
		Debug.Log(message: "ORIENTATION: " + ort.ToString());
		Debug.Log(message: "OFFSET-X: " + offsetX.ToString());
		Debug.Log(message: "OFFSET-y: " + offsetY.ToString());
	}

	void scaleSprites()
	{
		for(int i=0; i<5; i++)
		{
			arrows[i].localScale = new Vector3(scaleVal, scaleVal, 1);
		}
	}

	bool UpdateScore(int actual, int swiped)
	{
		if(actual==swiped)
		{
			score.color = new Color32(0, 174, 32, 255);
			sc+=pos;
		}
		else
		{
			score.color = new Color32(203, 32, 32, 255);
			sc-=neg;
		}
		// score.fontStyle = FontStyle.Bold;
		score.fontSize = 84;
		score.text = sc.ToString();
		return (actual==swiped);
	}

	void GameOver()
	{
		if(PlayerPrefs.GetInt("highscore", 0) < sc)
		{
			PlayerPrefs.SetInt("highscore", sc);
		}
		SceneManager.LoadScene("GameOverScene");
	}

	// Start is called only once at the start of scene
	void Start () {
		sc = 0;
		scaleSprites();
		assignDirection();
	}

	// Update is called once per frame
	void Update ()
	{
		timeLeft -= Time.deltaTime;
		if(! float.IsPositiveInfinity(timeLeft))
		{
			tl.text = "Time Left : " + timeLeft.ToString("0"); 
		}
		// Debug.Log("TIME LEFT: " + timeLeft.ToString());
		if(timeLeft < 0)
		{
			Debug.Log("Time Up.");
			GameOver();
		}

		if(!hasSwiped)
		{
			score.fontSize = 50;
			score.text = "Swipe in the direction of the middle arrow";
			if(SwipeManager.IsSwiping())
			{
				Debug.Log("Timer started.");
				hasSwiped = true;
				timeLeft = playTime;
				score.fontSize = 84;
				score.text = sc.ToString();
			}
		}

		if (SwipeManager.IsSwipingLeft()) {
			Debug.Log("LEFT");
			UpdateScore(dir, LEFT);
			assignDirection();
		}
		if(SwipeManager.IsSwipingRight()){
			Debug.Log("RIGHT");
			UpdateScore(dir, RIGHT);
			assignDirection();

		}
		if (SwipeManager.IsSwipingDown()) {
			Debug.Log("DOWN");
			UpdateScore(dir, DOWN);
			assignDirection();
		}
		if(SwipeManager.IsSwipingUp()){
			Debug.Log("UP");
			UpdateScore(dir, UP);
			assignDirection();
		}
		
	}
}
