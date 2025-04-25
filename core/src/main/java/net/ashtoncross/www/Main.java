/*
 * TF2 PONG
 *
 * This is a silly little project I made to learn about LibGDX.
 * TF2 is copyright of Valve software. I do not own the rights to
 * it.
 */

package net.ashtoncross.www;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    SpriteBatch batch;
    FitViewport viewport;
    Paddle leftPaddle;
    Paddle rightPaddle;
    Ball ball;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(32, 24);
        leftPaddle = new Paddle(3, 3);
        rightPaddle = new Paddle(29, 3);
        ball = new Ball(10, 10);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input(); // process inputs for paddles
        logic(); // process ball
        draw(); // draw
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            leftPaddle.moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            leftPaddle.moveDown();
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            rightPaddle.moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            rightPaddle.moveDown();
    }

    private void logic() {
        // stop paddles from leaving window
        float height = viewport.getWorldHeight();
        leftPaddle.setY(MathUtils.clamp(leftPaddle.getY(), 0, height - Paddle.paddleHeight));
        leftPaddle.update();
        rightPaddle.setY(MathUtils.clamp(rightPaddle.getY(), 0, height - Paddle.paddleHeight));
        rightPaddle.update();

        // TODO: if ball X is < 0 or > width, do score stuff.

        // ball bounces off top and bottom
        if (ball.getY() > height - 1 || ball.getY() < 0) {
            //ball.setY(MathUtils.clamp(ball.getX(), 0, height - 1));
            ball.velocity.y *= -1.1f;
        }

        // ball collisions with paddles
        if (ball.hitbox.overlaps(leftPaddle.hitbox)) {
            leftPaddle.bump(-0.2f);
            ball.velocity.x *= -1.1f;
        }

        if (ball.hitbox.overlaps(rightPaddle.hitbox)) {
            rightPaddle.bump(0.2f);
            ball.velocity.x *= -1.1f;
        }

        // out of bounds


        ball.update();
    }

    private void draw() {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.end();

        leftPaddle.render(viewport.getCamera().combined);
        rightPaddle.render(viewport.getCamera().combined);
        ball.render(viewport.getCamera().combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
