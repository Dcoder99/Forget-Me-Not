using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Game_Selector : MonoBehaviour {

	void FixedUpdate(){
        if (Application.platform == RuntimePlatform.Android)
        {
            if (Input.GetKey(KeyCode.Escape))
            {
                Application.Quit();
            }
        }
    }

	public void MC()
    {
        SceneManager.LoadScene("MC_StartGame");
    }

    public void PR()
    {
        SceneManager.LoadScene("MainMenu");
    }
    public void FB()
    {
        SceneManager.LoadScene("FB_GameScene");
    }
}
