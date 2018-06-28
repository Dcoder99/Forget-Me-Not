using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MapScript : MonoBehaviour
{

    public GameObject maxPrefab, path40Prefab, path80Prefab, turnPrefab, tPrefab, deadendPrefab, coin, textPrefab;
//    public static int maxCoinIdx = -1, targetIdx = 1;


    void generateLevel(int currentLevel)
    {
        Debug.Log("Generating Level..." + currentLevel);
        List<List<float>> level = levelPrefab.array[currentLevel];

        // prefab1 = {which_prefab, x_coordinate, z_coordinate, y_rotation, index_of_prefab}
        foreach (List<float> prefab1 in level)
        {
            if (prefab1[4] == -1f)
            {

                if((int)prefab1[0] == -1)
                {
                    PositionCamera.pos= new Vector3(prefab1[1],250, prefab1[2]);
                }
                Debug.Log("Instantiate prefab");

                //Instantiating Path40
                if ((int)prefab1[0] == 0)
                {
                    Instantiate(path40Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Path80
                else if ((int)prefab1[0] == 1)
                {
                    Instantiate(path80Prefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Turn 
                else if ((int)prefab1[0] == 2)
                {
                    Instantiate(turnPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating T Junction
                else if ((int)prefab1[0] == 3)
                {
                    Instantiate(tPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }

                //Instantiating Dead End
                else if ((int)prefab1[0] == 4)
                {
                    Instantiate(deadendPrefab, new Vector3(prefab1[1], 0, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                }
            }
            else
            {
                Debug.Log("Generating Coins!");
                // It's a coin!
                string coinName = prefab1[4].ToString();
               

                GameObject coinObject = Instantiate(coin, new Vector3(prefab1[1], 20, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                coinObject.transform.localScale += new Vector3(10, 10, 10);//changing the size of the coin object to see it from arial view

                textPrefab.GetComponent<Text>().text = "Checkpoint: "+ prefab1[4];
                Instantiate(textPrefab,coinObject.transform.position, transform.rotation);
                

                coinObject.name = coinName;
                /*if (prefab1[4] > maxCoinIdx)
                {
                    maxCoinIdx = (int)prefab1[4];
                }*/
            }
        }
    }
    IEnumerator LoadGameScene(float t)
    {
            yield return new WaitForSeconds(t);
            SceneManager.LoadScene("GameScene");
    }

    /*void Init()
    {
        Debug.Log("INIT");
        maxCoinIdx = -1;
        targetIdx = 1;

        //Instantiating Max
        //GameObject maxInstance =  Instantiate(maxPrefab, new Vector3(0, 0, 4), transform.rotation * Quaternion.Euler(0, 0, 0));
        //maxInstance.AddComponent<Collisions>();

        

    }*/
    void Start()
    {
        //Init();
        generateLevel(3);//PlayerPrefs.GetInt("currentLevel", 1));
        StartCoroutine(LoadGameScene(10f));

    }

    // Update is called once per frame
    void Update()
    {

    }
}
