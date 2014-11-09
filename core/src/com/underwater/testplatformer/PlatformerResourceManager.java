package com.underwater.testplatformer;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.ResolutionEntryVO;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by CyberJoe on 11/9/2014.
 */
public class PlatformerResourceManager extends ResourceManager {

    public ResolutionEntryVO currentResolution;

    public void initPlatformerResources() {
        loadProjectVO();

        // figure out the best resolution from the list of available resolutions for the existing screen size
        currentResolution = getBestResolutionMatch(getProjectVO());

        setWorkingResolution(currentResolution.name);

        initAllResources();
    }


    public ResolutionEntryVO getBestResolutionMatch(ProjectInfoVO projctInfoVo) {
        float deltaSize = Math.abs(projctInfoVo.originalResolution.height - Gdx.graphics.getHeight());
        ResolutionEntryVO result = projctInfoVo.originalResolution;

        for(int i = 0; i < projctInfoVo.resolutions.size(); i++) {
            float newDeltaSize = Math.abs(projctInfoVo.resolutions.get(i).height - Gdx.graphics.getHeight());
            System.out.println(newDeltaSize);
            if(deltaSize > newDeltaSize) {
                deltaSize = newDeltaSize;
                result = projctInfoVo.resolutions.get(i);
            }
        }

        return result;
    }

}
