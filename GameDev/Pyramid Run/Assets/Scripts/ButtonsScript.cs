using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ButtonsScript: MonoBehaviour {
    
    public static void loadSelectedScene(string scene)
    {
        SceneManager.LoadScene(scene);
    }

    public void nextLevel()
    {
        PlayerPrefs.SetInt("currentLevel", PlayerPrefs.GetInt("currentLevel", 0) + 1);
        loadSelectedScene("GameScene");
    }

    public void reloadLevel()
    {
        PlayerPrefs.SetInt("currentLevel", PlayerPrefs.GetInt("currentLevel", 1));
        loadSelectedScene("GameScene");
    }

    public void Quit()
    {
        Application.Quit();
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
            loadSelectedScene("MainMenu");
    }

    public void sceneCall(string scene)
    {
        loadSelectedScene(scene);
    }

}
