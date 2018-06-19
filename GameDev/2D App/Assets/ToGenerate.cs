using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;



public class ToGenerate : MonoBehaviour 
{
	public List<int> ColoredTiles = new List<int>();
	public Renderer[] Tiles= new Renderer[16];

	public int ColoredTile;
	public static int score=0;
	public static int n=3;
	float time=2.5f;
	public int flag=0;//flag value changes to 1 once pattern has been generated

	public Text Score;//FinalScore;

	public void End_Game()
		{		
         SceneManager.LoadScene (1);
		}

	
	

	IEnumerator ResetToBlack(float t)
    	{
			yield return new WaitForSeconds(t);
			for(int i=0;i<16;i++)
			{
				Tiles[i].GetComponent<Renderer>().material.color= Color.black;
			}
			flag =1;
    	}

	

	/*void Awake()
	{
		n=2;
		time=5.5f;
	}*/


	// Use this for initialization
	void Start () 
	{
		Debug.Log("ToGenerate.Start()");
		if(score!=0 && score%2 == 0 && time!=0)
		{
				time=time- 0.2f;
			}

		if(score!=0 && score%5==0 && time>0)
		{
			n++;
			}		
		
		Debug.Log("n = "+ n);
		Debug.Log("time= "+ time);
		Tiles= GetComponentsInChildren<Renderer>();
		for(int i=0; i<16;i++)
		{
			Tiles[i].material.color= Color.black;
			}

		for(int i=0; i<n; i++)
		{
			 do 
			{
				
     			ColoredTile = Random.Range(0,16);
  			} 
			while (ColoredTiles.Contains(ColoredTile));
			Tiles[ColoredTile].material.color= Color.magenta; 
  			ColoredTiles.Add(ColoredTile);
			Debug.Log("Colored Tile"+ ColoredTile);
		}
		
		StartCoroutine(ResetToBlack(time));
		flag=0;
		

		
		
	}
	
	// Update is called once per frame
	void Update () {
		Score.text = "Score " + score.ToString();
		
		
		/*if(score%5 == 0)
		{	
			time=time-0.5f;
			n++;
		}*/
		
	}
}
