package net.ashtoncross.www;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    public Rectangle hitbox;
    public ShapeRenderer shape;
    public Vector2 velocity;
    public Vector2 position;

    public Ball(float x, float y) {
        hitbox = new Rectangle(x, y, 1,1);
        shape = new ShapeRenderer();
        velocity = new Vector2(5, 5);
        position = new Vector2(x, y);
    }

    public void update() {
        final float delta = Gdx.graphics.getDeltaTime();
        position.add(velocity.x * delta, velocity.y * delta);

        hitbox.setX(position.x);
        hitbox.setY(position.y);
    }

    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void render(Matrix4 matrix) {
        shape.setProjectionMatrix(matrix);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(hitbox.x, hitbox.y, 1,1);
        shape.end();
    }
}
