using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Map : MonoBehaviour
{

    public GameObject maxPrefab, path40Prefab, path80Prefab, turnPrefab, tPrefab, deadendPrefab, coin;

    public void generateLevel(int currentLevel)
    {
        Debug.Log("Generating Level..." + currentLevel);
        List<List<float>> level = levelPrefab.array[currentLevel];

        // prefab1 = {which_prefab, x_coordinate, z_coordinate, y_rotation, index_of_prefab}
        foreach (List<float> prefab1 in level)
        {
            if (prefab1[4] == -1f)
            {
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
                // It's a coin!
               
                GameObject coinObject2 = Instantiate(coin, new Vector3(prefab1[1], 10, prefab1[2]), transform.rotation * Quaternion.Euler(0, prefab1[3], 0));
                coinObject2.transform.localScale += new Vector3(10,10,10);
			
            }
        }
    }

    void Start()
    {
        generateLevel(PlayerPrefs.GetInt("currentLevel", 1));
    }

    // Update is called once per frame
    void Update()
    {

    }
}
