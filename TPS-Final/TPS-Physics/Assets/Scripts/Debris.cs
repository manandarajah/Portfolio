using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Line
{
    Vector2 point1;
    Vector2 point2;
    float m;
    float b;
    bool vertical;

    public Line(Vector2 p1, Vector2 p2)
    {
        point1 = p1;
        point2 = p2;
        m = (p2.y - p1.y) / (p2.x - p1.x);
        b = (p1.y * p2.x - p2.y * p1.x) / (p2.x - p1.x);
        vertical = p1.x == p2.x;
    }

    float X(float y)
    {
        return vertical ? point1.x : (y - b) / m;
    }

    float Y(float x)
    {
        return vertical ? 0 : m * x + b;
    }

    public static Vector2 Intersection(Line l1, Line l2)
    {
        if (l1.vertical)
        {
            return new Vector2(l1.X(0), l2.Y(l1.X(0)));
        }
        if (l2.vertical)
        {
            return new Vector2(l2.X(0), l1.Y(l2.X(0)));
        }
        // If they are parallel
        if (l1.m == l2.m)
        {
            return new Vector2(Mathf.Infinity, Mathf.Infinity);
        }
        else
        {
            return new Vector2((l1.b - l2.b) / (l2.m - l1.m), 
                (l1.b * l2.m - l2.b * l1.m) / (l2.m - l1.m));
        }
    }
}

public class Debris : MonoBehaviour
{
    Mesh mesh;
    public Vector2[] points;
    Line[] xLines;
    Line[] yLines;
    Vector3[] originalVerts;
    // Start is called before the first frame update
    void Start()
    {

    }

    public void SetVertices()
    {
        mesh = GetComponent<MeshFilter>().mesh;
        originalVerts = mesh.vertices;
        xLines = new Line[2];
        yLines = new Line[2];

        xLines[0] = new Line(points[0], points[5]);
        xLines[1] = new Line(points[1], points[4]);
        yLines[0] = new Line(points[2], points[7]);
        yLines[1] = new Line(points[3], points[6]);

        Vector3[] verts = mesh.vertices;

        for(int i = 0; i < verts.Length; i++)
        {
            int xIn = LineIndex(originalVerts[i].x);
            int yIn = LineIndex(originalVerts[i].y);
            Vector2 intersection = Line.Intersection(xLines[xIn], yLines[yIn]);
            verts[i] = new Vector3(intersection.x, intersection.y, verts[i].z);
        }
        mesh.vertices = verts;
        mesh.RecalculateNormals();
    }
    int LineIndex(float coord)
    {
        return coord > 0 ? 1 : 0;
    }
    // Update is called once per frame
    void Update()
    {
        //SetVertices();
    }


}
