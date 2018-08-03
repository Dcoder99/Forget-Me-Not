using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Start_Game : MonoBehaviour {

    private int FirstImgIndex;
    public List<Sprite> images;
    private Image im;



    //To Generate First Image
    void Start () {
        im = GetComponent<Image>();

        int count = images.Count;            //No. of images in File   
        int index = Random.Range(0, count);  //Random Index Selection
        Debug.Log("Index=" + index);
        im.sprite = images[index];          //Displaying the First Image

        FirstImgIndex = index;              
        PlayerPrefs.SetInt("FirstImageIndex", FirstImgIndex);       //Setting Player Pref to the index of first image

    }

    public void Go()
    {
        SceneManager.LoadScene("MC_MainGame");
    }

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("Game_Selector");
        }
    }
}
