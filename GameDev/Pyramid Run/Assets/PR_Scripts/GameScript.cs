using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using TMPro;

public class GameScript : MonoBehaviour
{

    public GameObject maxPrefab, path40Prefab, path80Prefab, turnPrefab, tPrefab, deadendPrefab, coin, checkpoint;
    GameObject MAX, MapCamera, MapCanvas, GameCanvas;
    enum prefabs { path40, path80, turn, t_junct, dead_end, coin };
    enum atr { prefab_name, x_coordinate, z_coordinate, y_rotation, index_of_prefab };
    public static int maxCoinIdx = -1, targetIdx = 1;
    float map_x, map_y, map_z, angle_x = 0.4712f, angle_z = 0.3840f;
    public Text Levelno;

    GameObject getPrefab(float idx)
    {
        int choice = (int)idx;
        switch (choice)
        {
            case (int)prefabs.path40:
                return path40Prefab;
            case (int)prefabs.path80:
                return path80Prefab;
            case (int)prefabs.turn:
                return turnPrefab;
            case (int)prefabs.t_junct:
                return tPrefab;
            case (int)prefabs.dead_end:
                return deadendPrefab;
            default:
                Debug.Log("Prefab index Out of Bounds");
                return path40Prefab; // Default prefab
        }
    }

    void generateLevel(int currentLevel)
    {
        Debug.Log("Generating Level..." + currentLevel);
        List<List<float>> level = levelPrefab.array[currentLevel];
        float min_x = 0, min_z = 0, max_x = 0, max_z = 0;

        foreach (List<float> prefab1 in level)
        {
            if ((int)prefab1[(int)atr.prefab_name] != (int)prefabs.coin)
            {
                Debug.Log("Instantiate prefab");
                Instantiate(getPrefab(prefab1[(int)atr.prefab_name]), new Vector3(prefab1[(int)atr.x_coordinate], 0, prefab1[(int)atr.z_coordinate]), transform.rotation * Quaternion.Euler(0, prefab1[(int)atr.y_rotation], 0));
                min_x = Mathf.Min(min_x, prefab1[(int)atr.x_coordinate]);
                min_z = Mathf.Min(min_z, prefab1[(int)atr.z_coordinate]);
                max_x = Mathf.Max(max_x, prefab1[(int)atr.x_coordinate]);
                max_z = Mathf.Max(max_z, prefab1[(int)atr.z_coordinate]);
            }
            else
            {
                Debug.Log("Generating Coin!");
                string coinName = prefab1[(int)atr.index_of_prefab].ToString();
                GameObject coinObject = Instantiate(coin, new Vector3(prefab1[(int)atr.x_coordinate], 0, prefab1[(int)atr.z_coordinate]), transform.rotation * Quaternion.Euler(0, prefab1[(int)atr.y_rotation], 0));
                GameObject checkpointObject = Instantiate(checkpoint, new Vector3(prefab1[(int)atr.x_coordinate], 10, prefab1[(int)atr.z_coordinate]), transform.rotation * Quaternion.Euler(0, 0, 0));
                TextMeshPro mText = checkpointObject.GetComponentInChildren<TextMeshPro>();
                coinObject.name = coinName;
                mText.text = coinName;

                if (prefab1[(int)atr.index_of_prefab] > maxCoinIdx)
                {
                    maxCoinIdx = (int)prefab1[4];
                }
            }
        }
        Debug.Log("MIN X:Z " + min_x + " : " + min_z);
        Debug.Log("MAX X:Z " + max_x + " : " + max_z);
        map_x = (min_x + max_x) / 2.0f;
        map_z = (min_z + max_z) / 2.0f;
        map_y = Mathf.Max(((max_x - map_x) / Mathf.Tan(angle_x)), ((max_z - map_z) / Mathf.Tan(angle_z)));
    }

    void showMap()
    {
        // Disable max
        MAX.SetActive(false);
        // Enable Map canvas
        MapCanvas.SetActive(true);
        // Disable canvas
        GameCanvas.SetActive(false);
        // Position camera
        MapCamera.SetActive(true);
        MapCamera.transform.position = new Vector3(map_x, map_y, map_z);
        return;
    }

    public void destroyMap()
    {
        Debug.Log("in destroyMap()");
        // Enable max
        MAX.SetActive(true);
        // Disable  map canvas
        MapCanvas.SetActive(false);
        // Disable camera
        MapCamera.SetActive(false);
        // Enable Canvas
        GameCanvas.SetActive(true);
        Time.timeScale = 1f;
    }

    void Init()
    {
        Debug.Log("INIT");
        maxCoinIdx = -1;
        targetIdx = 1;
        Time.timeScale = 0f;
        MAX = GameObject.FindGameObjectWithTag("myMax");
        MapCanvas = GameObject.FindGameObjectWithTag("mapCanvas");
        GameCanvas = GameObject.FindGameObjectWithTag("gameCanvas");
        MapCamera = GameObject.FindGameObjectWithTag("mapCamera");
        generateLevel(PlayerPrefs.GetInt("currentLevel", 1));
        showMap();
    }
    void Start()
    {
        Init();
        Levelno.text = "LEVEL: " + PlayerPrefs.GetInt("currentLevel");
        Debug.Log(PlayerPrefs.GetInt("currentLevel"));
    }


}
