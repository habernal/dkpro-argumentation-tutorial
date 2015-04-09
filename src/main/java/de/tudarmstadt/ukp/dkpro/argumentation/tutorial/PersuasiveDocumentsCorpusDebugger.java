/*
 * Copyright 2015
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tudarmstadt.ukp.dkpro.argumentation.tutorial;

import de.tudarmstadt.ukp.dkpro.argumentation.types.PersuasivenessAnnotationMetaData;
import de.tudarmstadt.ukp.dkpro.argumentation.types.WebArgumentMetadata;
import de.tudarmstadt.ukp.dkpro.argumentation.types.WebArgumentMetadataHelper;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiReader;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import java.util.Collection;

/**
 * Reads the complete argumentation corpus from
 * <p/>
 * Habernal, I., Eckle-Kohler, J., & Gurevych, I. (2014). Argumentation Mining on the Web from
 * Information Seeking Perspective. In E. Cabrio, S. Villata, & A. Wyner (Eds.), Proceedings
 * of the Workshop on Frontiers and Connections between Argumentation Theory and Natural Language
 * Processing (pp. 26–39). Bertinoro, Italy: CEUR-WS. Retrieved from http://ceur-ws.org/Vol-1341/
 * <p/>
 * and prints documents labeled as persuasive/non-persuasive
 *
 * @author Ivan Habernal
 */
public class PersuasiveDocumentsCorpusDebugger
{
    public static void main(String[] args)
    {
        // TODO set this properly to "gold.data.persuasive" directory
        final String annotatedCorpusDir = "/home/user-ukp/research/data/argumentation/habernal.gurevych.2015.argumentation.mining.CL.data/data/gold.data.persuasive/";

        try {
            SimplePipeline.runPipeline(CollectionReaderFactory.createReaderDescription(
                            XmiReader.class,
                            XmiReader.PARAM_SOURCE_LOCATION, annotatedCorpusDir,
                            XmiReader.PARAM_PATTERNS, "[+]*.xmi"
                    ),
                    AnalysisEngineFactory.createEngineDescription(
                            PersuasiveDocumentDump.class
                    )
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the document text and gold data label (true if persuasive, false otherwise)
     */
    public static class PersuasiveDocumentDump
            extends JCasAnnotator_ImplBase
    {
        @Override
        public void process(JCas aJCas)
                throws AnalysisEngineProcessException
        {
            System.out.println("----------------");
            WebArgumentMetadata webArgumentMetadata = WebArgumentMetadataHelper.get(aJCas);

            Collection<PersuasivenessAnnotationMetaData> persuasivenessAnnotationMetaData = JCasUtil
                    .select(aJCas, PersuasivenessAnnotationMetaData.class);

            for (PersuasivenessAnnotationMetaData metaData : persuasivenessAnnotationMetaData) {
                if (metaData.getIsGold()) {
                    System.out.println(
                            "Doc#" + webArgumentMetadata.getOrigId() + " labeled as persuasive: "
                                    + metaData.getIsPersuasive());
                }
            }

            System.out.println(aJCas.getDocumentText());
        }
    }
}
