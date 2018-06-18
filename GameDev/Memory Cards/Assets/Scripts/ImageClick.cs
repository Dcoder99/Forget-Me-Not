using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ImageClick : MonoBehaviour {

    private int FirstIndex;
    private int Current_Score;
    public Text Display_score;


    void Start () {
        FirstIndex = PlayerPrefs.GetInt("FirstImageIndex");
        Current_Score = PlayerPrefs.GetInt("FinalScore");
    }
	
    public void Circle()
    {
        if(FirstIndex == 0)
        {
            Current_Score += 50;
            Display_score.color = new Color(0, 1, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();
        }

        else
        {
            Display_score.color = new Color(1, 0, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();  //Displaying the score
        }

        StartCoroutine(Wait());
        SceneManager.LoadScene("EndGame");
    }

    public void Triangle()
    {
        if (FirstIndex == 1)
        {
            Current_Score += 50;
            Display_score.color = new Color(0, 1, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();
        }
        else
        {
            Display_score.color = new Color(1, 0, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();  //Displaying the score
        }

        StartCoroutine(Wait());
        SceneManager.LoadScene("EndGame");
    }

    public void Square()
    {
        if (FirstIndex == 2)
        {
            Current_Score += 50;
            Display_score.color = new Color(0, 1, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();
        }
        else
        {
            Display_score.color = new Color(1, 0, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();  //Displaying the score
        }

        StartCoroutine(Wait());
        SceneManager.LoadScene("EndGame");
    }

    public void Pentagon()
    {
        if (FirstIndex == 3)
        {
            Current_Score += 50;
            Display_score.color = new Color(0, 1, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();
        }
        else
        {
            Display_score.color = new Color(1, 0, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();  //Displaying the score
        }

        StartCoroutine(Wait());
        SceneManager.LoadScene("EndGame");
    }

    public void Hexagon()
    {
        if (FirstIndex == 4)
        {
            Current_Score += 50;
            Display_score.color = new Color(0, 1, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();
        }
        else
        {
            Display_score.color = new Color(1, 0, 0, 1);
            Display_score.text = "Score: " + Current_Score.ToString();  //Displaying the score
        }

        StartCoroutine(Wait());
        SceneManager.LoadScene("EndGame");
    }

    IEnumerator Wait()
    {
        yield return new WaitForSeconds(3);
    }
}
