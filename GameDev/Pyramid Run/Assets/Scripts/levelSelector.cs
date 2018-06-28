using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class levelSelector : MonoBehaviour {

	public void setLevel(int currentLevel)
    {
        Debug.Log("Current level is:" + currentLevel);
        PlayerPrefs.SetInt("currentLevel",currentLevel);
        SceneManager.LoadScene("GameScene");
    }

}
