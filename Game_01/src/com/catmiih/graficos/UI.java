package com.catmiih.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.catmiih.main.Game;

public class UI {

	public void render(Graphics g) {
		//contagem de pontos
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,20));
		g.drawString("Pontos: "+Game.moeda_atual+"/"+Game.moedas_contagem, 20, 30);
	}
	
}
