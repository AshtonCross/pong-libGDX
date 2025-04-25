package net.ashtoncross.www;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {
    public Rectangle hitbox;
    public ShapeRenderer shape;
    public static final float paddleWidth = 0.3f;
    public static final float paddleHeight = 6;

    public Paddle(float x, float y) {
        hitbox = new Rectangle(x, y, paddleWidth, paddleHeight);
        shape = new ShapeRenderer();
    }

    public void moveUp() {
        move(1);
    }

    public void moveDown() {
        move(-1);
    }

    private void move(int direction) {
        final float speed = 25f;
        final float delta = Gdx.graphics.getDeltaTime();
        hitbox.setY(hitbox.getY() + (speed * direction * delta) );
    }

    public void render(Matrix4 matrix) {
        shape.setProjectionMatrix(matrix);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(hitbox.x, hitbox.y, paddleWidth,paddleHeight);
        shape.end();
    }

    public void setX(float x) {
        hitbox.setX(x);
    }

    public float getX() {
        return hitbox.getX();
    }

    public void setY(float y) {
        hitbox.setY(y);
    }

    public float getY() {
        return hitbox.getY();
    }
}
