/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.estrategia;

import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.PixelGrabber;

/**
 *
 * @author angel
 */
final class MyGifPixelGrabber implements ImageObserver
{
  private Image m_image=null;   
  private Object m_pixels=null; 
  private int m_iNumOfColors=0;  
  private int m_iWidth, m_iHeight;
  private ColorModel m_colorModel=null;

  public boolean isIndexed()
  {
	if (m_colorModel==null)
	  return false;

    return ((m_colorModel instanceof IndexColorModel));
  }

  MyGifPixelGrabber(Image img)
  {
    m_image=img;
  }

  public int getNumOfColors()
  {
    return m_iNumOfColors;
  }

  public void destroy()
  {
    m_image=null;
    m_pixels=null;
  }

  public void grabPixels()
  {
    m_iWidth=m_image.getWidth(this);
    m_iHeight=m_image.getHeight(this); 
    PixelGrabber pixelGrabber=new PixelGrabber(m_image, 0,0, m_iWidth, m_iHeight, false);  
    try
    {
      pixelGrabber.grabPixels();
    }
    catch (Exception e)
    {
      System.out.println("Excepción en PixelGrabber"); 
    }
	
    m_pixels=(Object)pixelGrabber.getPixels();
    m_colorModel=pixelGrabber.getColorModel();
	
    if (!(m_colorModel instanceof IndexColorModel))
    {
    }
    else
    {
      m_iNumOfColors=((IndexColorModel)m_colorModel).getMapSize();
    }
  }

  public Object getPixels()
  {
    return m_pixels; 
  }

  public int getWidth()
  {
    return m_iWidth;
  }

  public int getHeight()
  {
    return m_iHeight;
  }
  
  public ColorModel getColorModel()
  {
	return m_colorModel;
  }

  public int getRed(int pixel)
  {
    if ((m_colorModel instanceof IndexColorModel))	  
		return ((IndexColorModel)m_colorModel).getRed(pixel);
	else
		return ((DirectColorModel)m_colorModel).getRed(pixel);
  }

  public int getGreen(int pixel)
  {
    if ((m_colorModel instanceof IndexColorModel))	  
		return ((IndexColorModel)m_colorModel).getGreen(pixel);
	else
		return ((DirectColorModel)m_colorModel).getGreen(pixel);
  }

  public int getBlue(int pixel)
  {
    if ((m_colorModel instanceof IndexColorModel))	  
		return ((IndexColorModel)m_colorModel).getBlue(pixel);
	else
		return ((DirectColorModel)m_colorModel).getBlue(pixel);
  }

  public int getRGB(int pixel)
  {
    if ((m_colorModel instanceof IndexColorModel))	  
		return ((IndexColorModel)m_colorModel).getRGB(pixel);
	else
		return pixel;
  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) 
  {
    return true;	  
  }  
}
