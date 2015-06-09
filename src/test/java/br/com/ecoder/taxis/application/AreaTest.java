package br.com.ecoder.taxis.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AreaTest {

    @Test
    public void shouldParseSuccess() {

        Area area = new Area("-23.97463,-46.34727", "23.34374,43.64739");

        Assert.assertEquals(new Double(-23.97463), area.getLat1());
        Assert.assertEquals(new Double(-46.34727), area.getLng1());
        Assert.assertEquals(new Double(23.34374), area.getLat2());
        Assert.assertEquals(new Double(43.64739), area.getLng2());
        Assert.assertEquals(new Double(-0.3154450000000004), area.getCenterLat());
        Assert.assertEquals(new Double(-1.3499400000000001), area.getCenterLng());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateFailSW() {

        Area area = new Area("-23a.97463,-46.34727", "23.34374,43.64739");

        Assert.assertEquals(new Double(-23.97463), area.getLat1());
        Assert.assertEquals(new Double(-46.34727), area.getLng1());
        Assert.assertEquals(new Double(23.34374), area.getLat2());
        Assert.assertEquals(new Double(43.64739), area.getLng2());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateFailNE() {

        Area area = new Area("-23.97463,-46.34727", "23.34374,abc");

        Assert.assertEquals(new Double(-23.97463), area.getLat1());
        Assert.assertEquals(new Double(-46.34727), area.getLng1());
        Assert.assertEquals(new Double(23.34374), area.getLat2());
        Assert.assertEquals(new Double(43.64739), area.getLng2());
    }

}
