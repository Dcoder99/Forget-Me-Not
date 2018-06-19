using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class ToCheckClick : MonoBehaviour {
	public ToGenerate togenerate;
	static int no_of_clicks;
	int personalclick;// if a tile has been clicked once, its second click is not considered
	


	
		void OnMouseDown()
		{
			if(togenerate.flag == 1)
			{if(personalclick==0)
				{
					no_of_clicks++;
					personalclick++;
				}

			Debug.Log(gameObject.name + " is clicked");
			
			if(no_of_clicks<=ToGenerate.n && togenerate.ColoredTiles.Contains(transform.GetSiblingIndex()) && personalclick==1) 
			{
				gameObject.GetComponent<Renderer>().material.color= Color.magenta;
				if(no_of_clicks==ToGenerate.n)
				{
					ToGenerate.score++;
					Application.LoadLevel("Right Pattern");
				}	//changes colour of clicked tile to white
				
			}
			else if(no_of_clicks<=ToGenerate.n && !(togenerate.ColoredTiles.Contains(transform.GetSiblingIndex())))
			{
				gameObject.GetComponent<Renderer>().material.color= Color.red;	//changes colour of clicked tile to red
				Debug.Log("Wrong Tile Entered");
				Application.LoadLevel("Wrong Pattern");
				return;
			}

			else
			{
				
				Debug.Log("Figure out what to do");
			}
			}
		}
	
			
		




	void Start () {
		no_of_clicks=0;
		personalclick=0;
		GameObject Tile= GameObject.Find("Tile");
		togenerate= Tile.GetComponent<ToGenerate>();		
		
	}
	
	void Update () {
		
	}
}
