/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea.annotators.settings;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import com.mallowigi.idea.annotators.JSAnnotator;
import com.mallowigi.idea.annotators.TSAnnotator;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "HardCodedStringLiteral",
  "DialogTitleCapitalization",
  "ClassWithTooManyFields"})
public final class TSColorSettings extends BaseColorSettings {
  @NotNull
  @NonNls
  private static final AttributesDescriptor[] TS_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> TS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey TS_KEYWORD = TSAnnotator.KEYWORD;
  private static final TextAttributesKey TS_NUMBER = TSAnnotator.NUMBER;

  public static final TextAttributesKey THIS_SUPER = TSAnnotator.THIS_SUPER;
  private static final TextAttributesKey MODULE = TSAnnotator.MODULE;
  private static final TextAttributesKey DEBUGGER = TSAnnotator.DEBUGGER;
  public static final TextAttributesKey CONSOLE = TSAnnotator.CONSOLE;
  private static final TextAttributesKey NULL = TSAnnotator.NULL;
  private static final TextAttributesKey VAL = TSAnnotator.VAL;
  public static final TextAttributesKey FUNCTION = TSAnnotator.FUNCTION;

  private static final TextAttributesKey PRIVATE = TSAnnotator.PRIVATE;
  private static final TextAttributesKey DECLARE = TSAnnotator.DECLARE;
  private static final TextAttributesKey TYPE_ALIAS = TSAnnotator.TYPE_ALIAS;
  private static final TextAttributesKey PRIMITIVE = TSAnnotator.PRIMITIVE;
  private static final TextAttributesKey FUNCTION_NAME = JSAnnotator.FUNCTION;
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("JS.LOCAL_VARIABLE"),
    DefaultLanguageHighlighterColors.LOCAL_VARIABLE);

  static {
    TS_ATTRIBUTES = new AttributesDescriptor[]{
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.this.super"), THIS_SUPER),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.module.import.export.from"), MODULE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.debugger"), DEBUGGER),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.null.undefined"), NULL),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.var.let.const"), VAL),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.var.console"), CONSOLE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.function"), FUNCTION),

      new AttributesDescriptor(MaterialThemeBundle.message("keywords.private.public.protected"), PRIVATE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.declare"), DECLARE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.type.alias"), TYPE_ALIAS),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.true.false"), PRIMITIVE),

    };

    TS_DESCRIPTORS.putAll(createAdditionalHlAttrs());
    TS_DESCRIPTORS.putAll(JSColorSettings.JS_DESCRIPTORS);
  }

  @NotNull
  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("string", DefaultLanguageHighlighterColors.STRING);
    descriptors.put("private", PRIVATE);
    descriptors.put("declare", DECLARE);
    descriptors.put("type", TYPE_ALIAS);

    descriptors.put("class", DefaultLanguageHighlighterColors.CLASS_NAME);
    descriptors.put("keyword", TS_KEYWORD);
    descriptors.put("function", FUNCTION);
    descriptors.put("function_name", FUNCTION_NAME);
    descriptors.put("val", VAL);
    descriptors.put("local_variable", VARIABLE);
    descriptors.put("this", THIS_SUPER);
    descriptors.put("null", NULL);
    descriptors.put("primitive", PRIMITIVE);
    descriptors.put("debugger", DEBUGGER);
    descriptors.put("import", MODULE);
    descriptors.put("console", CONSOLE);
    descriptors.put("number", TS_NUMBER);
    descriptors.put("inst_field", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    return descriptors;
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.Any_type;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("TypeScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NonNls
  @NotNull
  @Override
  public String getDemoText() {
    return
      "<import>import</import> <local_variable>_</local_variable> <import>from</import> <string>'lodash'</string>;\n" +
        "<import>export</import> <declare>declare</declare> <keyword>interface</keyword> <class>MyInterface</class> " +
        "<import>from</import> " +
        "<string>'./myClass'</string>;\n" +
        "<import>export default</import> <class>MyClass</class>;\n" +
        "\n" +
        "<type>type</type> <class>MyFoo</class> = <class>MyInterface</class>;\n" +
        "\n" +
        "<keyword>class</keyword> <class>MyType</class> <keyword>extends</keyword> <class>AbstractClass</class> {\n" +
        "    " +
        "<private>private</private> <local_variable>field</local_variable>: <class>String</class>;\n" +
        "    " +
        "<private>protected</private> <local_variable>protect</local_variable>: <class>Number</class>;\n" +
        "    " +
        "<private>public</private> <local_variable>num</local_variable> = <number>10</number>;\n" +
        "\n" +
        "    " +
        "<function_name>foo</function_name>() {\n" +
        "      " +
        "<val>var</val> <local_variable>x</local_variable> = <number>10</number>;\n" +
        "      " +
        "<this>this</this>.<inst_field>x</inst_field> = <null>null</null>;\n" +
        "      " +
        "<keyword>if</keyword> (<local_variable>x</local_variable> === <null>undefined</null>) {\n" +
        "        " +
        "<console>console</console>.<function>log</function>(<string>'foo'</string>);\n" +
        "        " +
        "<debugger>debugger</debugger>;\n" +
        "        " +
        "<keyword>return</keyword> <primitive>false</primitive>;\n" +
        "      " +
        "}\n" +
        "      " +
        "<keyword>return</keyword> <primitive>true</primitive>;\n" +
        "    " +
        "}\n" +
        "\n" +
        "}";
  }

  @NotNull
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return Collections.unmodifiableMap(TS_DESCRIPTORS);
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return TS_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("TSColorPage.ts.additions");
  }

  @NotNull
  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
