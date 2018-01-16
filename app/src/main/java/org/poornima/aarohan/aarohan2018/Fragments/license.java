package org.poornima.aarohan.aarohan2018.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.poornima.aarohan.aarohan2018.R;

public class license extends Fragment {

    public license() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_license, container, false);
        TextView l1 = view.findViewById(R.id.l1);
        TextView l2 = view.findViewById(R.id.l2);
        TextView l3 = view.findViewById(R.id.l3);
        TextView l4 = view.findViewById(R.id.l4);
        l1.setText(Html.fromHtml("<pre><code>Copyright 2013 Square, Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.</code></pre>"));

        l2.setText(Html.fromHtml("The MIT License (MIT)\n" +
                "\n" +
                "Copyright (c) 2014 Ramotion\n" +
                "\n" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                "of this software and associated documentation files (the \"Software\"), to deal\n" +
                "in the Software without restriction, including without limitation the rights\n" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                "copies of the Software, and to permit persons to whom the Software is\n" +
                "furnished to do so, subject to the following conditions:\n" +
                "\n" +
                "The above copyright notice and this permission notice shall be included in all\n" +
                "copies or substantial portions of the Software.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                "SOFTWARE.\n" +
                "\n" +
                "\n" +
                "<p>The MIT License (MIT)</p>\n" +
                "<p>Copyright (c) 2014 Ramotion</p>\n" +
                "<p>Permission is hereby granted, free of charge, to any person obtaining a copy<br />of this software and associated documentation files (the \"Software\"), to deal<br />in the Software without restriction, including without limitation the rights<br />to use, copy, modify, merge, publish, distribute, sublicense, and/or sell<br />copies of the Software, and to permit persons to whom the Software is<br />furnished to do so, subject to the following conditions:</p>\n" +
                "<p>The above copyright notice and this permission notice shall be included in all<br />copies or substantial portions of the Software.</p>\n" +
                "<p>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR<br />IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,<br />FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE<br />AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER<br />LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,<br />OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE<br />SOFTWARE.</p>"));
        l3.setText(Html.fromHtml("<p>&nbsp;</p>\n" +
                "<p>MIT License<br />Copyright (c) 2013 - present Karol Wr&oacute;tniak, Droids on Roids</p>\n" +
                "<p>Permission is hereby granted, free of charge, to any person obtaining a copy<br />of this software and associated documentation files (the \"Software\"), to deal<br />in the Software without restriction, including without limitation the rights<br />to use, copy, modify, merge, publish, distribute, sublicense, and/or sell<br />copies of the Software, and to permit persons to whom the Software is<br />furnished to do so, subject to the following conditions:</p>\n" +
                "<p>The above copyright notice and this permission notice shall be included in<br />all copies or substantial portions of the Software.</p>\n" +
                "<p>THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR<br />IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,<br />FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE<br />AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER<br />LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,<br />OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN<br />THE SOFTWARE.</p>\n" +
                "<p>GIFLIB:<br />The GIFLIB distribution is Copyright (c) 1997 Eric S. Raymond</p>\n" +
                "<p>ReLinker:<br />Copyright 2015 KeepSafe Software, Inc.</p>\n" +
                "<p>Licensed under the Apache License, Version 2.0 (the \"License\");<br />you may not use this file except in compliance with the License.<br />You may obtain a copy of the License at</p>\n" +
                "<p>http://www.apache.org/licenses/LICENSE-2.0</p>\n" +
                "<p>Unless required by applicable law or agreed to in writing, software<br />distributed under the License is distributed on an \"AS IS\" BASIS,<br />WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br />See the License for the specific language governing permissions and<br />limitations under the License.</p>\n" +
                "<p>memset.arm.S:<br />Copyright (C) 2010 The Android Open Source Project</p>\n" +
                "<p>Licensed under the Apache License, Version 2.0 (the \"License\");<br />you may not use this file except in compliance with the License.<br />You may obtain a copy of the License at</p>\n" +
                "<p>http://www.apache.org/licenses/LICENSE-2.0</p>\n" +
                "<p>Unless required by applicable law or agreed to in writing, software<br />distributed under the License is distributed on an \"AS IS\" BASIS,<br />WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br />See the License for the specific language governing permissions and<br />limitations under the License.</p>\n" +
                "<p>memset32_neon.S:<br />Copyright (c) 2009,2010, Code Aurora Forum. All rights reserved.<br />Use of this source code is governed by a BSD-style license that can be<br />found in the LICENSE file (SKIA section).</p>\n" +
                "<p>&nbsp;</p>"));
        l4.setText(Html.fromHtml("<p><br /> Apache License<br /> Version 2.0, January 2004<br /> http://www.apache.org/licenses/</p>\n" +
                "<p>TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION</p>\n" +
                "<p>1. Definitions.</p>\n" +
                "<p>\"License\" shall mean the terms and conditions for use, reproduction,<br /> and distribution as defined by Sections 1 through 9 of this document.</p>\n" +
                "<p>\"Licensor\" shall mean the copyright owner or entity authorized by<br /> the copyright owner that is granting the License.</p>\n" +
                "<p>\"Legal Entity\" shall mean the union of the acting entity and all<br /> other entities that control, are controlled by, or are under common<br /> control with that entity. For the purposes of this definition,<br /> \"control\" means (i) the power, direct or indirect, to cause the<br /> direction or management of such entity, whether by contract or<br /> otherwise, or (ii) ownership of fifty percent (50%) or more of the<br /> outstanding shares, or (iii) beneficial ownership of such entity.</p>\n" +
                "<p>\"You\" (or \"Your\") shall mean an individual or Legal Entity<br /> exercising permissions granted by this License.</p>\n" +
                "<p>\"Source\" form shall mean the preferred form for making modifications,<br /> including but not limited to software source code, documentation<br /> source, and configuration files.</p>\n" +
                "<p>\"Object\" form shall mean any form resulting from mechanical<br /> transformation or translation of a Source form, including but<br /> not limited to compiled object code, generated documentation,<br /> and conversions to other media types.</p>\n" +
                "<p>\"Work\" shall mean the work of authorship, whether in Source or<br /> Object form, made available under the License, as indicated by a<br /> copyright notice that is included in or attached to the work<br /> (an example is provided in the Appendix below).</p>\n" +
                "<p>\"Derivative Works\" shall mean any work, whether in Source or Object<br /> form, that is based on (or derived from) the Work and for which the<br /> editorial revisions, annotations, elaborations, or other modifications<br /> represent, as a whole, an original work of authorship. For the purposes<br /> of this License, Derivative Works shall not include works that remain<br /> separable from, or merely link (or bind by name) to the interfaces of,<br /> the Work and Derivative Works thereof.</p>\n" +
                "<p>\"Contribution\" shall mean any work of authorship, including<br /> the original version of the Work and any modifications or additions<br /> to that Work or Derivative Works thereof, that is intentionally<br /> submitted to Licensor for inclusion in the Work by the copyright owner<br /> or by an individual or Legal Entity authorized to submit on behalf of<br /> the copyright owner. For the purposes of this definition, \"submitted\"<br /> means any form of electronic, verbal, or written communication sent<br /> to the Licensor or its representatives, including but not limited to<br /> communication on electronic mailing lists, source code control systems,<br /> and issue tracking systems that are managed by, or on behalf of, the<br /> Licensor for the purpose of discussing and improving the Work, but<br /> excluding communication that is conspicuously marked or otherwise<br /> designated in writing by the copyright owner as \"Not a Contribution.\"</p>\n" +
                "<p>\"Contributor\" shall mean Licensor and any individual or Legal Entity<br /> on behalf of whom a Contribution has been received by Licensor and<br /> subsequently incorporated within the Work.</p>\n" +
                "<p>2. Grant of Copyright License. Subject to the terms and conditions of<br /> this License, each Contributor hereby grants to You a perpetual,<br /> worldwide, non-exclusive, no-charge, royalty-free, irrevocable<br /> copyright license to reproduce, prepare Derivative Works of,<br /> publicly display, publicly perform, sublicense, and distribute the<br /> Work and such Derivative Works in Source or Object form.</p>\n" +
                "<p>3. Grant of Patent License. Subject to the terms and conditions of<br /> this License, each Contributor hereby grants to You a perpetual,<br /> worldwide, non-exclusive, no-charge, royalty-free, irrevocable<br /> (except as stated in this section) patent license to make, have made,<br /> use, offer to sell, sell, import, and otherwise transfer the Work,<br /> where such license applies only to those patent claims licensable<br /> by such Contributor that are necessarily infringed by their<br /> Contribution(s) alone or by combination of their Contribution(s)<br /> with the Work to which such Contribution(s) was submitted. If You<br /> institute patent litigation against any entity (including a<br /> cross-claim or counterclaim in a lawsuit) alleging that the Work<br /> or a Contribution incorporated within the Work constitutes direct<br /> or contributory patent infringement, then any patent licenses<br /> granted to You under this License for that Work shall terminate<br /> as of the date such litigation is filed.</p>\n" +
                "<p>4. Redistribution. You may reproduce and distribute copies of the<br /> Work or Derivative Works thereof in any medium, with or without<br /> modifications, and in Source or Object form, provided that You<br /> meet the following conditions:</p>\n" +
                "<p>(a) You must give any other recipients of the Work or<br /> Derivative Works a copy of this License; and</p>\n" +
                "<p>(b) You must cause any modified files to carry prominent notices<br /> stating that You changed the files; and</p>\n" +
                "<p>(c) You must retain, in the Source form of any Derivative Works<br /> that You distribute, all copyright, patent, trademark, and<br /> attribution notices from the Source form of the Work,<br /> excluding those notices that do not pertain to any part of<br /> the Derivative Works; and</p>\n" +
                "<p>(d) If the Work includes a \"NOTICE\" text file as part of its<br /> distribution, then any Derivative Works that You distribute must<br /> include a readable copy of the attribution notices contained<br /> within such NOTICE file, excluding those notices that do not<br /> pertain to any part of the Derivative Works, in at least one<br /> of the following places: within a NOTICE text file distributed<br /> as part of the Derivative Works; within the Source form or<br /> documentation, if provided along with the Derivative Works; or,<br /> within a display generated by the Derivative Works, if and<br /> wherever such third-party notices normally appear. The contents<br /> of the NOTICE file are for informational purposes only and<br /> do not modify the License. You may add Your own attribution<br /> notices within Derivative Works that You distribute, alongside<br /> or as an addendum to the NOTICE text from the Work, provided<br /> that such additional attribution notices cannot be construed<br /> as modifying the License.</p>\n" +
                "<p>You may add Your own copyright statement to Your modifications and<br /> may provide additional or different license terms and conditions<br /> for use, reproduction, or distribution of Your modifications, or<br /> for any such Derivative Works as a whole, provided Your use,<br /> reproduction, and distribution of the Work otherwise complies with<br /> the conditions stated in this License.</p>\n" +
                "<p>5. Submission of Contributions. Unless You explicitly state otherwise,<br /> any Contribution intentionally submitted for inclusion in the Work<br /> by You to the Licensor shall be under the terms and conditions of<br /> this License, without any additional terms or conditions.<br /> Notwithstanding the above, nothing herein shall supersede or modify<br /> the terms of any separate license agreement you may have executed<br /> with Licensor regarding such Contributions.</p>\n" +
                "<p>6. Trademarks. This License does not grant permission to use the trade<br /> names, trademarks, service marks, or product names of the Licensor,<br /> except as required for reasonable and customary use in describing the<br /> origin of the Work and reproducing the content of the NOTICE file.</p>\n" +
                "<p>7. Disclaimer of Warranty. Unless required by applicable law or<br /> agreed to in writing, Licensor provides the Work (and each<br /> Contributor provides its Contributions) on an \"AS IS\" BASIS,<br /> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or<br /> implied, including, without limitation, any warranties or conditions<br /> of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A<br /> PARTICULAR PURPOSE. You are solely responsible for determining the<br /> appropriateness of using or redistributing the Work and assume any<br /> risks associated with Your exercise of permissions under this License.</p>\n" +
                "<p>8. Limitation of Liability. In no event and under no legal theory,<br /> whether in tort (including negligence), contract, or otherwise,<br /> unless required by applicable law (such as deliberate and grossly<br /> negligent acts) or agreed to in writing, shall any Contributor be<br /> liable to You for damages, including any direct, indirect, special,<br /> incidental, or consequential damages of any character arising as a<br /> result of this License or out of the use or inability to use the<br /> Work (including but not limited to damages for loss of goodwill,<br /> work stoppage, computer failure or malfunction, or any and all<br /> other commercial damages or losses), even if such Contributor<br /> has been advised of the possibility of such damages.</p>\n" +
                "<p>9. Accepting Warranty or Additional Liability. While redistributing<br /> the Work or Derivative Works thereof, You may choose to offer,<br /> and charge a fee for, acceptance of support, warranty, indemnity,<br /> or other liability obligations and/or rights consistent with this<br /> License. However, in accepting such obligations, You may act only<br /> on Your own behalf and on Your sole responsibility, not on behalf<br /> of any other Contributor, and only if You agree to indemnify,<br /> defend, and hold each Contributor harmless for any liability<br /> incurred by, or claims asserted against, such Contributor by reason<br /> of your accepting any such warranty or additional liability.</p>\n" +
                "<p>END OF TERMS AND CONDITIONS</p>\n" +
                "<p>APPENDIX: How to apply the Apache License to your work.</p>\n" +
                "<p>To apply the Apache License to your work, attach the following<br /> boilerplate notice, with the fields enclosed by brackets \"[]\"<br /> replaced with your own identifying information. (Don't include<br /> the brackets!) The text should be enclosed in the appropriate<br /> comment syntax for the file format. We also recommend that a<br /> file or class name and description of purpose be included on the<br /> same \"printed page\" as the copyright notice for easier<br /> identification within third-party archives.</p>\n" +
                "<p>Copyright [yyyy] [name of copyright owner]</p>\n" +
                "<p>Licensed under the Apache License, Version 2.0 (the \"License\");<br /> you may not use this file except in compliance with the License.<br /> You may obtain a copy of the License at</p>\n" +
                "<p>http://www.apache.org/licenses/LICENSE-2.0</p>\n" +
                "<p>Unless required by applicable law or agreed to in writing, software<br /> distributed under the License is distributed on an \"AS IS\" BASIS,<br /> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br /> See the License for the specific language governing permissions and<br /> limitations under the License.</p>\n" +
                "<p>========================================================================<br />jai-imageio<br />========================================================================</p>\n" +
                "<p>Copyright (c) 2005 Sun Microsystems, Inc.<br />Copyright &copy; 2010-2014 University of Manchester<br />Copyright &copy; 2010-2015 Stian Soiland-Reyes<br />Copyright &copy; 2015 Peter Hull<br />All Rights Reserved.</p>\n" +
                "<p>Redistribution and use in source and binary forms, with or without<br />modification, are permitted provided that the following conditions<br />are met:</p>\n" +
                "<p>- Redistribution of source code must retain the above copyright<br /> notice, this list of conditions and the following disclaimer.</p>\n" +
                "<p>- Redistribution in binary form must reproduce the above copyright<br /> notice, this list of conditions and the following disclaimer in<br /> the documentation and/or other materials provided with the<br /> distribution.</p>\n" +
                "<p>Neither the name of Sun Microsystems, Inc. or the names of<br />contributors may be used to endorse or promote products derived<br />from this software without specific prior written permission.</p>\n" +
                "<p>This software is provided \"AS IS,\" without a warranty of any<br />kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND<br />WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,<br />FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY<br />EXCLUDED. SUN MIDROSYSTEMS, INC. (\"SUN\") AND ITS LICENSORS SHALL<br />NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF<br />USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS<br />DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR<br />ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,<br />CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND<br />REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR<br />INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE<br />POSSIBILITY OF SUCH DAMAGES.</p>\n" +
                "<p>You acknowledge that this software is not designed or intended for<br />use in the design, construction, operation or maintenance of any<br />nuclear facility.</p>"));
        return view;

    }

}
