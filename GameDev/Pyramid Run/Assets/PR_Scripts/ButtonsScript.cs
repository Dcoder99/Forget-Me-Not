using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ButtonsScript: MonoBehaviour {

    public GameObject pausecanvas;


    public static void loadSelectedScene(string scene)
    {
        SceneManager.LoadScene(scene);
    }

    //To Load Next Level
    public void nextLevel()
    {
        PlayerPrefs.SetInt("currentLevel", PlayerPrefs.GetInt("currentLevel", 0) + 1);
        loadSelectedScene("GameScene");
    }

    //To Reload Current Level
    public void reloadLevel()
    {
        Time.timeScale = 1f;
        PlayerPrefs.SetInt("currentLevel", PlayerPrefs.GetInt("currentLevel", 1));
        loadSelectedScene("GameScene");
    }

    //To Quit The Game
    public void Quit()
    {
        Application.Quit();
    }

    //To go to MainMenu on pressing Back Button 
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
            loadSelectedScene("MainMenu");
    }


    //To Call a Scene
    public void sceneCall(string scene)
    {
        Time.timeScale = 1f;
        loadSelectedScene(scene);
    }


    //To Pause
    public void Pause()
    {
        if (Time.timeScale == 0f)
        {
            return;
        }
        Time.timeScale = 0f;
        Instantiate(pausecanvas, new Vector3(0, 0, 0), transform.rotation * Quaternion.Euler(0, 0, 0));
    }

    //To Resume
    public void resume()
    {
        Time.timeScale = 1f;
        Destroy(pausecanvas);
    }

}
