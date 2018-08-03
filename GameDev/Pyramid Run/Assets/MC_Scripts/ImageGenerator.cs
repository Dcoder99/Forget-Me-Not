using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class ImageGenerator : MonoBehaviour
{

    public List<Sprite> images;
    
    private Image im;
    public Text txt;
    
    public static int score ;
    private int Previous , Current ;
    
    private float TimeLeft=0;

 //---------------------------------------------------------------------------------------------------------------
    void Start()
    {
        im = GetComponent<Image>();
        
        
        Previous = PlayerPrefs.GetInt("FirstImageIndex");
        
        //Generating Second Image
        Current = GenerateImage();    

    }
    
//----------------------------------------------------------------------------------------------------------------
    public int GenerateImage()
    {

        StartCoroutine("Fadein");
        
        int count = images.Count;            //No. of images in File   
        int index = Random.Range(0, count);  //Random Index Selection
        Debug.Log("Index=" + index);
        im.sprite = images[index];   //Displaying the image

        StartCoroutine("Fadeout");

        return index;
    }
//----------------------------------------------------------------------------------------------------------------
    //Calculating Score if "same" option selected
    public void Score_Same()
    {
        
        if(Previous == Current)
        {
            score++;
            txt.color = new Color(0, 1, 0, 1);
            txt.text = "Score: " + score.ToString();  //Displaying the score
        }

        else
        {
            txt.color = new Color(1, 0, 0, 1);
            txt.text = "Score: " + score.ToString();  //Displaying the score
        }
        
        Previous = Current;         //Index of current image goes to 'Previous'
        Current = GenerateImage();     //Index of the Generated Image
    }
    //------------------------------------------------------------------------------------------------------------
    //Calculating Score if "different" option selected
    public void Score_Diff()
    {

         if (Previous != Current)
        {
            score++;
            txt.color = new Color(0, 1, 0, 1);
            txt.text = "Score: " + score.ToString();  //Displaying the score
        }

        else
        {
            txt.color = new Color(1, 0, 0, 1);
            txt.text = "Score: " + score.ToString();  //Displaying the score
        }

        Previous = Current;       //Index of current image goes to 'Previous'
        
        Current = GenerateImage();             //Index of the Generated Image
    }

    void Update()
    {     
        TimeLeft -= UnityEngine.Time.deltaTime;
        
                if (TimeLeft < -30)
        {
            PlayerPrefs.SetInt("FinalScore", score);
        }
        
        if (Input.GetKeyDown(KeyCode.Escape))
        {   
            Application.Quit();
        }
    }

    IEnumerator Fadein()
    {
        for (float f = 1f; f >= 0; f -= 0.1f)
        {
            
            if (im.tag == "Main_Image")
            {

                Debug.Log("In FadeIn");
                Color c = im.material.color;
                c.a = f;
                im.material.color = c;
            }
            yield return null;
        }
    }



    IEnumerator Fadeout()
    {
        for (float f = 0f; f <= 1; f += 0.1f)
        {

            if (im.tag == "Main_Image")
            {
                Debug.Log("In FadeOut");
                Color c = im.material.color;
                c.a = f;
                im.material.color = c;
            }
            yield return null;
        }
    }

}








