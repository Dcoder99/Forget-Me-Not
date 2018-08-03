using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class EndGame : MonoBehaviour {

    private int FinalS;
    public Text Display_score ;

	void Start () {
        FinalS = PlayerPrefs.GetInt("FinalScore");
        Debug.Log("FINAL SCORE:" + FinalS.ToString());

        Display_score.color = new Color(0.5f, 0.5f, 0.5f, 1);
        Display_score.text = "Your Score: "+FinalS.ToString();
        
    }
	
	public void Restart()
    {
        SceneManager.LoadScene("MC_StartGame");
    }

    public void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            Application.Quit();
        }
    }
}
