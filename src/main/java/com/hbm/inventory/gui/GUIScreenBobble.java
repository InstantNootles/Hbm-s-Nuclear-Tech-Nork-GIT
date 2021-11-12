package com.hbm.inventory.gui;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.generic.BlockBobble.TileEntityBobble;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GUIScreenBobble extends GuiScreen {
	
	TileEntityBobble bobble;

	public GUIScreenBobble(TileEntityBobble bobble) {
		this.bobble = bobble;
	}

	@Override
	public void initGui() {
		mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("hbm:block.bobble"), 1.0F));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		
		this.drawDefaultBackground();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		double sizeX = 300;
		double sizeY = 150;
		double left = (this.width - sizeX) / 2;
		double top = (this.height - sizeY) / 2;
		
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.setColorRGBA_F(0F, 0.2F, 0F, 0.8F);
		tess.addVertex(left + sizeX, top, this.zLevel);
		tess.addVertex(left, top, this.zLevel);
		tess.addVertex(left, top + sizeY, this.zLevel);
		tess.addVertex(left + sizeX, top + sizeY, this.zLevel);
		tess.draw();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		
		int nextLevel = (int)top + 10;

		String bobbleTitle = "Nuclear Tech Commemorative Bobblehead";
		this.fontRendererObj.drawStringWithShadow(bobbleTitle, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(bobbleTitle) / 2), nextLevel, 0x00ff00);
		
		nextLevel += 10;
		
		String bobbleName = this.bobble.type.name;
		this.fontRendererObj.drawStringWithShadow(bobbleName, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(bobbleName) / 2), nextLevel, 0x009900);
		
		nextLevel += 20;
		
		if(this.bobble.type.contribution != null) {

			String title = "Has contributed";
			this.fontRendererObj.drawStringWithShadow(title, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(title) / 2), nextLevel, 0x00ff00);
			
			nextLevel += 10;
			
			String text = this.bobble.type.contribution;
			this.fontRendererObj.drawStringWithShadow(text, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(text) / 2), nextLevel, 0x009900);

			nextLevel += 20;
		}
		
		if(this.bobble.type.inscription != null) {

			String title = "The bottom has following inscription:";
			this.fontRendererObj.drawStringWithShadow(title, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(title) / 2), nextLevel, 0x00ff00);
			
			nextLevel += 10;

			String[] list = this.bobble.type.inscription.split("\\$");
			for(String text : list) {
				this.fontRendererObj.drawStringWithShadow(text, (int)(left + sizeX / 2 - this.fontRendererObj.getStringWidth(text) / 2), nextLevel, 0x009900);
				nextLevel += 10;
			}

			nextLevel += 10;
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	@Override
	protected void keyTyped(char c, int key) {
		if(key == 1 || key == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.thePlayer.closeScreen();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}